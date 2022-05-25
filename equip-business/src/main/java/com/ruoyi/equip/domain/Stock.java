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
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存管理对象 tb_stock
 *
 * @author dengn
 * @date 2022-01-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_stock")
@ApiModel("库存管理对象")
public class Stock extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -1287004053921248304L;
    /** 主键id */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    /** 单据编号 */
    @Excel(name = "单据编号")
    @ApiModelProperty("单据编号")
    private String billNumber;

    /** 单据时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "单据时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("单据时间")
    private Date billDate;

    /** 变动前 */
    @Excel(name = "变动前")
    @ApiModelProperty("变动前")
    private BigDecimal befNum;

    /** 单据数量 */
    @Excel(name = "单据数量")
    @ApiModelProperty("单据数量")
    private BigDecimal billNum;

    /** 变动后 */
    @Excel(name = "变动后")
    @ApiModelProperty("变动后")
    private BigDecimal aftNum;

    /** 单据状态 */
    @Excel(name = "单据状态")
    @ApiModelProperty("单据状态")
    private Long type;

    /** 入库部门名 */
    @Excel(name = "入库部门名")
    @ApiModelProperty("入库部门名")
    private String inDeptName;

    /** 出库部门名 */
    @Excel(name = "出库部门名")
    @ApiModelProperty("出库部门名")
    private String outDeptName;

    /** 入库部门id */
    @Excel(name = "入库部门id")
    @ApiModelProperty("入库部门id")
    private Long inDeptId;

    /** 入库仓库id */
    @Excel(name = "入库仓库id")
    @ApiModelProperty("入库仓库id")
    private Long inWarId;

    /** 出库部门id */
    @Excel(name = "出库部门id")
    @ApiModelProperty("出库部门id")
    private Long outDeptId;

    /** 出库仓库id */
    @Excel(name = "出库仓库id")
    @ApiModelProperty("出库仓库id")
    private Long outWarId;

    /** 入库仓库名 */
    @Excel(name = "入库仓库名")
    @ApiModelProperty("入库仓库名")
    private String inWarName;

    /** 出库仓库名 */
    @Excel(name = "出库仓库名")
    @ApiModelProperty("出库仓库名")
    private String outWarName;

    /** 调拨单id */
    @Excel(name = "调拨单id")
    @ApiModelProperty("调拨单id")
    private Long allocationId;

    /** 调拨单号 */
    @Excel(name = "调拨单号")
    @ApiModelProperty("调拨单号")
    private String allocationNumber;
}
