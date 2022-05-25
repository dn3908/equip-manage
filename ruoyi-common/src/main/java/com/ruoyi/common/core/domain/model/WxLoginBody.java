package com.ruoyi.common.core.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信用户登录对象
 * 
 * @author weibocy
 */
@Data
@ApiModel("微信用户登录对象")
public class WxLoginBody
{
    /**
     * 授权码
     */
    @ApiModelProperty("授权码")
    private String code;

    /**
     * 登录令牌
     */
    @ApiModelProperty("登录令牌")
    private String token;

}
