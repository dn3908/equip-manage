package com.ruoyi.framework.web.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.BizUser;
import com.ruoyi.common.core.domain.model.BizLoginUser;
import com.ruoyi.common.core.domain.model.WxAuthBody;
import com.ruoyi.common.core.domain.model.WxLoginBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactoryForBusiness;
import com.ruoyi.system.service.IBizUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 业务登录校验方法
 * 
 * @author weibocy
 */
@Component
@ConditionalOnProperty(prefix = "ruoyi", name = "name", havingValue = "business-api")
public class BizLoginService
{
    @Autowired
    private TokenServiceForBusiness tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IBizUserService userService;

    @Autowired
    private WxService wxService;

    /**
     * 登录验证
     *
     * @param appId 微信应用id
     * @param loginBody 微信登录表单
     * @return 结果
     */
    public String login(String appId, WxLoginBody loginBody) throws WxErrorException
    {
        String userIdk = "";
        String code = loginBody.getCode();
        String token = loginBody.getToken();
        String unionid = "";
        String openid = "";

        // 令牌不为空 校验是否有效，有效则成功登录
        if (StringUtils.isNotBlank(token)){
            BizLoginUser loginUser = tokenService.getLoginUserByToken(token);

            if (loginUser != null) {
                userIdk = StringUtils.isNotBlank(loginUser.getUser().getUnionid()) ? loginUser.getUser().getUnionid() : loginUser.getUser().getOpenid();
                AsyncManager.me().execute(AsyncFactoryForBusiness.recordLogininfor(userIdk, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
                return tokenService.createToken(loginUser);
            }
        }

        // 请求微信拿 unionid openid
        WxMaJscode2SessionResult sessionResult = wxService.getWxSession(appId, code);
        // 获取 unionid
        unionid = sessionResult.getUnionid();
        // 获取 openid
        openid = sessionResult.getOpenid();

        String loginFied = "unionid";

        if (StringUtils.isNotBlank(unionid)) {
            userIdk = unionid;
        }else {
            userIdk = openid;
            loginFied = "openid";
        }

        BizUser user = userService.lambdaQuery().eq(BizUser::getUnionid, userIdk).or().eq(BizUser::getOpenid, userIdk).one();
        if (user == null && loginFied.equals("unionid")){
            // 找不到可能是刚有unid 再用openid 去查查看
            userIdk = openid;
            user = userService.lambdaQuery().eq(BizUser::getUnionid, userIdk).or().eq(BizUser::getOpenid, userIdk).one();
        }

        // 判断如果用户不存在 则先自动创建
        if (user == null) {
            user = new BizUser();
            user.setOpenid(openid);
            user.setUnionid(unionid);
            userService.save(user);
        }

        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userIdk, "wxAppPwd"));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactoryForBusiness.recordLogininfor(userIdk, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactoryForBusiness.recordLogininfor(userIdk, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactoryForBusiness.recordLogininfor(userIdk, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        BizLoginUser loginUser = (BizLoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 微信授权信息更新
     * @param loginUser 登录用户实体
     * @param authBody 授权信息表单实体
     * @return
     */
    public BizLoginUser wxAuth( BizLoginUser loginUser, WxAuthBody authBody) {
        BizUser user = loginUser.getUser();
        user.setNickName(authBody.getNickname()); // 昵称
        user.setAvatar(authBody.getAvatar()); // 头像
        user.setSex(String.valueOf(authBody.getSex())); // 性别
        user.setCountry(authBody.getCountry()); // 国家
        user.setProvince(authBody.getProvince()); // 省份
        user.setCity(authBody.getCity()); // 城市

        Boolean updateRes = userService.updateById(user);
        if (!updateRes){
            throw new CustomException("更新用户信息失败");
        }

        loginUser.setUser(user);
        // 刷新缓存
        tokenService.refreshToken(loginUser);

        return loginUser;
    }
}
