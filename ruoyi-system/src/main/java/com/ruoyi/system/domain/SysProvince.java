package com.ruoyi.system.domain;

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

/**
 * 省份数据对象 sys_province
 *
 * @author weibocy
 * @date 2021-07-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_province")
@ApiModel("省份数据对象")
public class SysProvince extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId(value = "code", type = IdType.AUTO)
    @ApiModelProperty("${column.columnComment}")
    private String code;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @ApiModelProperty("${column.columnComment}")
    private String name;


}
