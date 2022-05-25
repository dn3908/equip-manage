package com.ruoyi.framework.web.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.WxMaConfiguration;
import com.ruoyi.framework.config.WxPayConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 微信业务处理
 *
 * @author weibocy
 */
@Component
@ConditionalOnProperty(prefix = "ruoyi", name = "name", havingValue = "business-api")
public class WxService {

    /**
     * 获取 微信session
     * @param appid 应用id
     * @param code 授权码
     * @return
     */
    public WxMaJscode2SessionResult getWxSession(String appid, String code) throws WxErrorException {
        if (StringUtils.isBlank(code)){
            throw new CustomException("授权码无效");
        }
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            return session;
        } catch (WxErrorException e) {
            throw e;
        }
    }


    /**
     * 返回指定的微信支付服务
     * @param appid 应用id
     * @return
     */
    public WxPayService getPayService(String appid) {
        final WxPayService payService = WxPayConfiguration.getPayService(appid);
        return payService;
    }

}
