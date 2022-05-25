package com.ruoyi.equip.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单据明细对象 tb_bill_sub
 *
 * @author dengn
 * @date 2022-01-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_bill_sub")
@ApiModel("单据明细对象")
public class BillSub extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6392613763693300973L;
    /** 子单id */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("子单id")
    private Long id;

    /** 装备id */
    @Excel(name = "装备id")
    @ApiModelProperty("装备id")
    private Long equipmentId;

    /** 装备名 */
    @Excel(name = "装备名")
    @ApiModelProperty("装备名")
    private String equipmentName;

    /** 主单id */
    @Excel(name = "主单id")
    @ApiModelProperty("主单id")
    private Long billId;

    /** 装备类型id */
    @Excel(name = "装备类型id")
    @ApiModelProperty("装备类型id")
    private Long equipmentTypeId;

    /** 装备类型名 */
    @Excel(name = "装备类型名")
    @ApiModelProperty("装备类型名")
    private String equipmentTypeName;

    /** 固定编码 */
    @Excel(name = "固定编码")
    @ApiModelProperty("固定编码")
    private String fixed;

    /** 装备数量 */
    @Excel(name = "装备数量")
    @ApiModelProperty("装备数量")
    private BigDecimal equipmentNum;

    /** 规格型号 */
    @Excel(name = "规格型号")
    @ApiModelProperty("规格型号")
    private String specifications;

    /** 单位 */
    @Excel(name = "单位")
    @ApiModelProperty("单位")
    private String unit;

    /** 装备金额 */
    @Excel(name = "装备金额")
    @ApiModelProperty("装备金额")
    private BigDecimal amount;
}
