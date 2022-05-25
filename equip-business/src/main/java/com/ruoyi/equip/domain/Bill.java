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
import java.util.Date;
import java.util.List;

/**
 * 单据主对象 tb_bill
 *
 * @author dengn
 * @date 2022-01-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_bill")
@ApiModel("单据主对象")
public class Bill extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4027232598322842582L;
    /** 单据id */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("单据id")
    private Long id;

    /** 单据编号 */
    @Excel(name = "单据编号")
    @ApiModelProperty("单据编号")
    private String number;

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

    /** 入库部门名 */
    @Excel(name = "入库部门名")
    @ApiModelProperty("入库部门名")
    private String inDeptName;

    /** 出库部门名 */
    @Excel(name = "出库部门名")
    @ApiModelProperty("出库部门名")
    private String outDeptName;

    /** 入库仓库名 */
    @Excel(name = "入库仓库名")
    @ApiModelProperty("入库仓库名")
    private String inWarName;

    /** 出库仓库名 */
    @Excel(name = "出库仓库名")
    @ApiModelProperty("出库仓库名")
    private String outWarName;

    /** 接收人 */
    @Excel(name = "接收人")
    @ApiModelProperty("接收人")
    private Long receiver;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private Long status;

    /** 接收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("接收时间")
    private Date receiverDate;

    /** 审核人id */
    @Excel(name = "审核人id")
    @ApiModelProperty("审核人id")
    private Long auditId;

    /** 审核人名 */
    @Excel(name = "审核人名")
    @ApiModelProperty("审核人名")
    private String auditName;

    /** 审核描述 */
    @Excel(name = "审核描述")
    @ApiModelProperty("审核描述")
    private String auditRemark;

    /** 单据类型 */
    @Excel(name = "单据类型")
    @ApiModelProperty("单据类型")
    private Long type;

    /** 调拨单id */
    @Excel(name = "调拨单id")
    @ApiModelProperty("调拨单id")
    private Long allocationId;

    /** 调拨单号 */
    @Excel(name = "调拨单号")
    @ApiModelProperty("调拨单号")
    private String allocationNumber;

    /** 单据日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "单据日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("单据日期")
    private Date billDate;

    /** 单据明细信息 */
    private List<BillSub> billSubList;
}
