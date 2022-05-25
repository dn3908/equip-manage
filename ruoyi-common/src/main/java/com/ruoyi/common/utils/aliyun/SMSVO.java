package com.ruoyi.common.utils.aliyun;

import lombok.Data;

@Data
public class SMSVO {

    /**
     * 是否发送成功
     */
    private boolean success;

    /**
     * 返回信息
     */
    private String msg;
}
