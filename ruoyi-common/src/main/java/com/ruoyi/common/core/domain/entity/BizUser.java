package com.ruoyi.common.core.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;



/**
 * 会员列表对象 biz_user
 *
 * @author weibocy
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("biz_user")
@ApiModel("会员列表对象")
public class BizUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Long userId;

    /** unionid */
    @ApiModelProperty("unionid")
    private String unionid;

    /** openid */
    @ApiModelProperty("openid")
    private String openid;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    @ApiModelProperty("用户昵称")
    private String nickName;

    /** 手机号码 */
    @Excel(name = "手机号码")
    @ApiModelProperty("手机号码")
    private String phonenumber;

    /** 用户性别（1男 2女 0未知） */
    @Excel(name = "用户性别", readConverterExp = "1=男,2=女,0=未知")
    @ApiModelProperty("用户性别（1男 2女 0未知）")
    private String sex;

    /** 头像地址 */
    @Excel(name = "头像地址")
    @ApiModelProperty("头像地址")
    private String avatar;

    /** 国家 */
    @Excel(name = "国家")
    @ApiModelProperty("国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    @ApiModelProperty("省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    @ApiModelProperty("城市")
    private String city;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    @ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;

    /** 是否是机器人（0否，2是） */
    @ApiModelProperty("是否是机器人（0否，2是）")
    private String isRobot;

    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /** 最后登录IP */
    @ApiModelProperty("最后登录IP")
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("最后登录时间")
    private Date loginDate;


}