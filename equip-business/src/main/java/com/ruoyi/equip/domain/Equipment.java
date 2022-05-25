package com.ruoyi.equip.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 装备信息
 */
@Data
@Accessors(chain = true)
@TableName("tb_equipment")
@ApiModel(value = "装备表")
public class Equipment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7818610532864836044L;

    /** 装备id */
    @TableId(value = "equipment_id", type = IdType.AUTO)
    @ApiModelProperty("装备id")
    private Long equipmentId;

    /** 装备名称 */
    @Excel(name = "装备名称")
    @ApiModelProperty("装备名称")
    private String equipmentName;

    /** 原始价值 */
    @Excel(name = "原始价值")
    @ApiModelProperty("原始价值")
    private BigDecimal originalValue;

    /** 临时编码 */
    @Excel(name = "临时编码")
    @ApiModelProperty("临时编码")
    private String temporary;

    /** 固定编码 */
    @Excel(name = "固定编码")
    @ApiModelProperty("固定编码")
    private String fixed;

    /** 规格型号 */
    @Excel(name = "规格型号")
    @ApiModelProperty("规格型号")
    private String specifications;

    /** 装备类型id */
    @Excel(name = "装备类型id")
    @ApiModelProperty("装备类型id")
    private Long equipmentTypeId;

    /** 装备类型名称 */
    @Excel(name = "装备类型名称")
    @ApiModelProperty("装备类型名称")
    private String equipmentTypeName;

    /** 单位 */
    @Excel(name = "单位")
    @ApiModelProperty("单位")
    private String unit;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("生产日期")
    private Date productionDate;

    /** 采购日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("采购日期")
    private Date purchaseDate;

    /** 来源 */
    @Excel(name = "来源")
    @ApiModelProperty("来源")
    private String source;

    /** 资产状态 1、闲置 2 借用 3 调拨中 4 在用 5 停用 6 维修中 7 报废 */
    @Excel(name = "资产状态 1、闲置 2 借用 3 调拨中 4 在用 5 停用 6 维修中 7 报废")
    @ApiModelProperty("资产状态 1、闲置 2 借用 3 调拨中 4 在用 5 停用 6 维修中 7 报废")
    private Long status;



}
