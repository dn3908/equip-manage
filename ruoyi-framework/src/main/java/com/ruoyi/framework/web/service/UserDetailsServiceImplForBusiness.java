package com.ruoyi.framework.web.service;


import com.ruoyi.common.core.domain.entity.BizUser;
import com.ruoyi.common.core.domain.model.BizLoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.IBizUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 业务用户验证处理
 *
 * @author weibocy
 */
@Service
@ConditionalOnProperty(prefix = "ruoyi", name = "name", havingValue = "business-api")
public class UserDetailsServiceImplForBusiness implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImplForBusiness.class);

    @Autowired
    private IBizUserService userService;


    @Override
    public UserDetails loadUserByUsername(String userIdk) throws UsernameNotFoundException
    {
        if (StringUtils.isBlank(userIdk)){
            throw new UsernameNotFoundException("授权码无效？");
        }
        BizUser user = userService.lambdaQuery()
                .eq(BizUser::getOpenid, userIdk)
                .or()
                .eq(BizUser::getUnionid, userIdk).one();

        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", userIdk);
            throw new UsernameNotFoundException("用户不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", userIdk);
            throw new BaseException("对不起，您的账号：" + userIdk + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", userIdk);
            throw new BaseException("对不起，您的账号：" + userIdk + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(BizUser user)
    {
        return new BizLoginUser(user);
    }
}
