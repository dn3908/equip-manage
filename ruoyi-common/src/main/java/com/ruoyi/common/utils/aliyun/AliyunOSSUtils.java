package com.ruoyi.common.utils.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliyunOSSUtils {

    private static  AliyunOSSUtils aliyunOSSUtils;

    /**
     * AccessKeyID
     */
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId = null;
    /**
     * accessKeySecret
     */
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret = null;
    /**
     * 地域地址
     */
    @Value("${aliyun.oss.endpoint}")
    private String endpoint = null;
    /**
     * bucket名称
     */
    @Value("${aliyun.oss.bucketName}")
    private String bucketName = null;
    /**
     * 存储在OSS中的前缀名
     */
    @Value("${aliyun.oss.filePrefix}")
    private String filePrefix = null;
    /**
     * 绑定域名
     */
    @Value("${aliyun.oss.domain}")
    private String domain = null;


    /**
     * 私有化构造
     */
    private AliyunOSSUtils() {

    }

    @PostConstruct
    public void init(){
        aliyunOSSUtils = this;
        aliyunOSSUtils.accessKeyId = this.accessKeyId;
        aliyunOSSUtils.accessKeySecret = this.accessKeySecret;
        aliyunOSSUtils.endpoint = this.endpoint;
        aliyunOSSUtils.bucketName = this.bucketName;
        aliyunOSSUtils.filePrefix = this.filePrefix;
        aliyunOSSUtils.domain = this.domain;

    }

    /**
     * 获取图片的URL头信息
     *
     * @return 返回url头信息
     */
    private static String getURLHead() {

        /**
         * 如果指定了绑定域名，则直接返回绑定的域名即可
         */
        if (StringUtils.isNotBlank(aliyunOSSUtils.domain)){
            return aliyunOSSUtils.domain + "/";
        }

        //从哪个位置截取
        int cutPoint = aliyunOSSUtils.endpoint.lastIndexOf('/') + 1;
        //http头
        String head = aliyunOSSUtils.endpoint.substring(0, cutPoint);
        //服务器地址信息
        String tail = aliyunOSSUtils.endpoint.substring(cutPoint);
        //返回结果
        return head + aliyunOSSUtils.bucketName + "." + tail + "/";
    }

    /**
     * 获取存储在服务器上的地址
     *
     * @param oranName 文件名
     * @return 文件URL
     */
    private static String getRealName(String oranName) {
        return getURLHead() + oranName;
    }

    /**
     * 获取一个随机的文件名
     *
     * @param oranName 初始的文件名
     * @return 返回加uuid后的文件名
     */
    private static String getRandomImageName(String oranName) {
        //获取一个uuid 去掉-
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //查一下是否带路径
        int cutPoint = oranName.lastIndexOf("/") + 1;
        //如果存在路径
        if (cutPoint != 0) {
            //掐头 如果开头是/ 则去掉
            String head = oranName.indexOf("/") == 0 ? oranName.substring(1, cutPoint) : oranName.substring(0, cutPoint);
            //去尾
            String tail = oranName.substring(cutPoint);
            //返回正确的带路径的图片名称
            return aliyunOSSUtils.filePrefix + head + uuid + tail;
        }
        //不存在 直接返回
        return aliyunOSSUtils.filePrefix + uuid + oranName;
    }

    /**
     * MultipartFile2File
     * @param multipartFile
     * @return
     */
    private static File transferToFile(MultipartFile multipartFile) {
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            //获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            //获取最后一个"."的位置
            int cutPoint = originalFilename.lastIndexOf(".");
            //获取文件名
            String prefix = originalFilename.substring(0,cutPoint);
            //获取后缀名
            String suffix = originalFilename.substring(cutPoint + 1);
            //创建临时文件
            file = File.createTempFile(prefix, suffix);
            //multipartFile2file
            multipartFile.transferTo(file);
            //删除临时文件
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 上传文件流
     *
     * @param oranFileName 上传到服务器上的文件路径和名称
     * @param file         来自本地的文件或者文件流
     */
    public static String uploadFileInputSteam(String oranFileName, MultipartFile file) {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectName = getRandomImageName(oranFileName);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliyunOSSUtils.endpoint, aliyunOSSUtils.accessKeyId, aliyunOSSUtils.accessKeySecret);

        // 上传文件流
        try (InputStream inputStream = new FileInputStream(transferToFile(file))) {
            //上传到OSS
            ossClient.putObject(aliyunOSSUtils.bucketName, objectName, inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回文件在服务器上的全路径+名称
        return getRealName(objectName);
    }


    /**
     * 上传文件流
     *
     * @param oranFileName 上传到服务器上的文件路径和名称
     * @param file         来自本地的文件或者文件流
     */
    public static String uploadFileInputSteam(String oranFileName, File file) {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectName = getRandomImageName(oranFileName);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliyunOSSUtils.endpoint, aliyunOSSUtils.accessKeyId, aliyunOSSUtils.accessKeySecret);

        // 上传文件流。
        try (InputStream inputStream = new FileInputStream(file);) {
            //上传到OSS
            ossClient.putObject(aliyunOSSUtils.bucketName, objectName, inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回文件在服务器上的全路径+名称
        return getRealName(objectName);
    }

}
