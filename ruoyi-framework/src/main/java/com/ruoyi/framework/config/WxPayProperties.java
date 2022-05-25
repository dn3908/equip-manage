package com.ruoyi.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

import java.util.List;


/**
 * 微信支付配置
 */
@ConfigurationProperties(prefix = "wx.pay")
@ConditionalOnProperty(prefix = "ruoyi", name = "name", havingValue = "business-api")
@Primary
public class WxPayProperties {

    /**
     * 考虑到小程序矩阵的应用场景
     */
    private List<Config> configs;

    public WxPayProperties(List<Config> configs) {
        this.configs = configs;
    }

    public static class Config {
        /**
         * 设置微信公众号或者小程序等的appid
         */
        private String appId;

        /**
         * 微信支付商户号
         */
        private String mchId;

        /**
         * 微信支付商户密钥
         */
        private String mchKey;

        /**
         * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
         */
        private String subAppId;

        /**
         * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
         */
        private String subMchId;

        /**
         * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
         */
        private String keyPath;

        /**
         * 通知回调地址
         */
        private String notifyUrl;


        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getMchKey() {
            return mchKey;
        }

        public void setMchKey(String mchKey) {
            this.mchKey = mchKey;
        }

        public String getSubAppId() {
            return subAppId;
        }

        public void setSubAppId(String subAppId) {
            this.subAppId = subAppId;
        }

        public String getSubMchId() {
            return subMchId;
        }

        public void setSubMchId(String subMchId) {
            this.subMchId = subMchId;
        }

        public String getKeyPath() {
            return keyPath;
        }

        public void setKeyPath(String keyPath) {
            this.keyPath = keyPath;
        }


        public String getNotifyUrl() {
            return notifyUrl;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }
}
