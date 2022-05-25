package com.ruoyi.common.utils.aliyun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 阿里云手机短信工具类
 * todo 将模版封装到配置文件中
 * todo 最好能让客户端有UUID机制配合，存放和取用时都用UUID配合
 * todo 缺少发送限制地址（单号码每天限制量）
 */
@Component
public class AliyunSMSUtils {

    /**
     * 配合PostConstruct初始化时进行静态变量初始化
     */
    private static AliyunSMSUtils aliyunSMSUtils;

    @Autowired
    private AliyunSmsProperty smsProperty;

    @Autowired
    private RedisCache cache;


    /**
     * 下面是一些默认的配置，无需修改
     * REGION_ID 服务器大区   不建议修改
     * SYS_DOMAIN API地址   不可修改
     * SYS_VERSION 系统版本 不可修改
     * SYS_ACTION API接口名必须为SendSms 不可修改
     */
    private static final String REGION_ID = "cn-hangzhou";
    private static final String SYS_DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String SYS_VERSION = "2017-05-25";
    private static final String SYS_ACTION = "SendSms";

    /**
     * 短信模版MAP
     */
//    private static Map<String, AliyunSmsProperty.Template> templateMap = new HashMap<>();

    /**
     * 创建profile对象
     */
    private static DefaultProfile profile = null;

    /**
     * 创建client对象
     */
    private static IAcsClient client = null;

    /**
     * 创建request对象
     */
    private static CommonRequest request = null;



    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        aliyunSMSUtils = this;
        aliyunSMSUtils.smsProperty = this.smsProperty;
        aliyunSMSUtils.cache = this.cache;

        //初始化profile
        profile = DefaultProfile.getProfile(
                REGION_ID,
                this.smsProperty.getAccessKeyId(),
                this.smsProperty.getAccessKeySecret()
        );
        client = new DefaultAcsClient(profile);

        //初始化一些参数
        request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion(SYS_VERSION);
        request.setSysAction(SYS_ACTION);
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("SignName", this.smsProperty.getSignName());

        // 将模板数据组装到map方便调用
//        this.smsProperty.getTemplates().stream().forEach(item -> {
//            templateMap.put(item.getCode())
//        });
    }



    /**
     * 校验手机号是否合法
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        return PhoneFormatCheckUtils.isChinaPhoneLegal(phoneNumber);
    }

    /**
     * 发送手机短信验证码
     * @param phoneNumber 手机号码
     * @return
     */
    public static SMSVO sendVCode(String phoneNumber) {

        //创建result
        SMSVO result = new SMSVO();
        //默认为false，只有成功时为true
        result.setSuccess(false);
        //判断是否为手机号
        if(!isPhoneNumber(phoneNumber)){
            result.setMsg("手机号码不合法！");
            return result;
        }

        //获取六位随机数
        String code = (long) (Math.random() * (999999 - 100000) + 100000) + "";
        Integer minutes = 6;

        //初始化用户自定义参数
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateCode", "SMS_21850218");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\", \"minutes\":\"" + minutes + "\"}");

        //发送的业务逻辑
        try {
            CommonResponse response = client.getCommonResponse(request);
            // 确保短信发送成功再写入缓存
            aliyunSMSUtils.cache.setCacheObject(phoneNumber, code, minutes, TimeUnit.MINUTES);
        } catch (Exception e) {
            result.setMsg("本次发送失败，请查看错误信息：" + e.getMessage());
            e.printStackTrace();
            return result;
        }
        //成功则返回正确的信息
        result.setMsg("短信发送成功！");
        result.setSuccess(true);
        return result;
    }


    /**
     * 检查手机短信验证码是否正确
     * @param phoneNumber
     * @param code
     * @return
     */
    public static Boolean checkVCode(String phoneNumber, String code) {
        String v = aliyunSMSUtils.cache.getCacheObject(phoneNumber);
        if (StringUtils.isBlank(v)){
            return false;
        }
        return true;
    }
}
