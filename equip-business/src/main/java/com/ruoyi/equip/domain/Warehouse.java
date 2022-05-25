package com.ruoyi.equip.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@TableName("tb_warehouse")
@ApiModel(value = "仓库表")
public class Warehouse extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4412670356800288583L;

    /** 仓库id */
    @TableId(value = "war_id", type = IdType.AUTO)
    @ApiModelProperty("仓库id")
    private Long warId;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    @ApiModelProperty("仓库名称")
    private String warName;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    @ApiModelProperty("仓库地址")
    private String address;

    /** 部门id */
    @Excel(name = "部门id")
    @ApiModelProperty("部门id")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    @ApiModelProperty("部门名称")
    private String deptName;

}
