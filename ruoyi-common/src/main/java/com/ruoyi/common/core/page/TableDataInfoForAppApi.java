package com.ruoyi.common.core.page;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象 业务api专用
 * 兼容yapi识别
 * 
 * @author weibocy
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("分页响应对象")
public class TableDataInfoForAppApi<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    @ApiModelProperty("总记录数")
    private long total;

    /** 列表数据 */
    @ApiModelProperty("列表数据")
    private List<T> rows;

    /** 消息状态码 */
    @ApiModelProperty("消息状态码")
    private int code;

    /** 消息内容 */
    @ApiModelProperty("消息内容")
    private String msg;

    /**
     * 返回成功
     * @param list 数据集合
     * @param <T>
     * @return
     */
    public static <T> TableDataInfoForAppApi<T> ok(List<T> list)
    {
        return restResult(list);
    }


    /**
     * 处理
     * @param list 数据集合
     * @param <T>
     * @return
     */
    private static <T> TableDataInfoForAppApi<T> restResult(List<T> list)
    {
        TableDataInfoForAppApi<T> rspData = new TableDataInfoForAppApi<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}
