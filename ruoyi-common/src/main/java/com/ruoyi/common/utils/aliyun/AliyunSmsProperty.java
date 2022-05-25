package com.ruoyi.common.utils.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "aliyun.sms")
@Component
public class AliyunSmsProperty {
    /**
     * AccessKeyID
     */
    private String accessKeyId = null;

    /**
     * accessKeySecret
     */
    private String accessKeySecret = null;

    /**
     * 短信签名
     */
    private String signName = null;

//    /**
//     * 多个模板配置信息
//     */
//    private List<Template> templates;
//
//
//    /**
//     * 模板配置
//     */
//    @Data
//    public static class Template {
//        /**
//         * 模版code
//         */
//        private String code;
//
//        /**
//         * 模板中的变量名称 例如模板中为${code} 此处就填写code
//         */
//        private String param;
//
//    }


}
