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
@TableName("tb_equipment_type")
@ApiModel(value = "装备类型表")
public class EquipmentType extends BaseEntity implements Serializable {

    /** 装备分类父类id */
    @Excel(name = "装备分类父类id")
    @ApiModelProperty("装备分类父类id")
    private Long equipmentTypePid;

    /** 装备类型id */
    @TableId(value = "equipment_type_id", type = IdType.AUTO)
    @ApiModelProperty("装备类型id")
    private Long equipmentTypeId;

    /** 装备类型名称 */
    @Excel(name = "装备类型名称")
    @ApiModelProperty("装备类型名称")
    private String equipmentTypeName;



}
