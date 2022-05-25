package com.ruoyi.framework.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
@ConditionalOnProperty(prefix = "ruoyi", name = "name", havingValue = "business-api")
public class WxPayConfiguration {

    private WxPayProperties properties;

    private static Map<String, WxPayService> payServices;

    @PostConstruct
    public void init(){
        List<WxPayProperties.Config> configs = this.properties.getConfigs();
        if (configs == null || configs.size() == 0) {
            throw new WxRuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
        }

        payServices = configs.stream().map(p -> {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(StringUtils.trimToNull(p.getAppId()));
            payConfig.setMchId(StringUtils.trimToNull(p.getMchId()));
            payConfig.setMchKey(StringUtils.trimToNull(p.getMchKey()));
            payConfig.setSubAppId(StringUtils.trimToNull(p.getSubAppId()));
            payConfig.setSubMchId(StringUtils.trimToNull(p.getSubMchId()));
            payConfig.setKeyPath(StringUtils.trimToNull(p.getKeyPath()));
            payConfig.setNotifyUrl(StringUtils.trimToNull(p.getNotifyUrl()));
            // 沙箱环境
            payConfig.setUseSandboxEnv(false);
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            return wxPayService;
        }).collect(Collectors.toMap(s -> s.getConfig().getAppId(), p -> p));

    }

    /**
     * 根据appid获取支付服务
     * @param appid
     * @return
     */
    public static WxPayService getPayService(String appid){
        WxPayService wxService = payServices.get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        return wxService;

    }

    public WxPayConfiguration(WxPayProperties properties) {
        this.properties = properties;
    }

    public WxPayProperties getProperties() {
        return properties;
    }

    public void setProperties(WxPayProperties properties) {
        this.properties = properties;
    }
}
