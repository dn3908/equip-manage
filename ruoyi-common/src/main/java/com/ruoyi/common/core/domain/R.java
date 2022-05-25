package com.ruoyi.common.core.domain;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 封装向客户端发回的响应数据
 */
@Data
@NoArgsConstructor
@ApiModel(description = "返回响应数据")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 默认成功代码 */
    public static final Integer SUCCESS = HttpStatus.SUCCESS;

    /** 默认失败代码 */
    public static final Integer FAIL = HttpStatus.ERROR;

    /**
     * 响应是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应状态码")
    private Integer code;
    /**
     * 响应信息
     */
    @ApiModelProperty(value = "响应信息")
    private String msg;
    /**
     * 成功返回的数据
     */
    @ApiModelProperty(value = "成功返回的数据")
    private T data;

    /**
     * 返回成功（空数据）
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> ok()
    {
        return restResult(null, SUCCESS, "操作成功");
    }


    /**
     * 返回成功（返回其他业务数据）
     *
     * @param data 数据对象
     * @param <T>
     * @return
     */
    public static <T> R<T> ok(T data)
    {
        return restResult(data, SUCCESS, "操作成功");
    }

    /**
     * 返回成功（返回其他业务数据）
     * @param data 数据对象
     * @param msg 自定义消息
     * @param <T>
     * @return
     */
    public static <T> R<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    /**
     * 返回失败（空数据）
     * @param <T>
     * @return
     */
    public static <T> R<T> fail()
    {
        return restResult(null, FAIL, "操作失败");
    }

    /**
     * 返回失败（自定义消息）
     * @param msg 自定义消息信息
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    /**
     * 返回失败（返回其他业务数据）
     * @param data 其他业务数据
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(T data)
    {
        return restResult(data, FAIL, "操作失败");
    }

    /**
     * 返回失败（返回其他业务数据，自定义消息）
     * @param data 其他业务数据
     * @param msg 自定义消息
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    /**
     * 返回失败（自定义状态码，自定义消息）
     * @param code  自定义状态码
     * @param msg 自定义消息
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(Integer code, String msg)
    {
        return restResult(null, code, msg);
    }


    private static <T> R<T> restResult(T data, Integer code, String msg)
    {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
