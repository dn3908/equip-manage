package com.ruoyi.framework.mybatisplus;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MP注入处理器
 *
 */
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            //根据属性名字设置要填充的值
            if (metaObject.hasGetter("createTime")) {
                if (metaObject.getValue("createTime") == null) {
                    this.setFieldValByName("createTime", new Date(), metaObject);
                }
            }
            // todo 业务端没有username，需要改造后再使用
//            if (metaObject.hasGetter("createBy")) {
//                if (metaObject.getValue("createBy") == null) {
//                    this.setFieldValByName("createBy", SecurityUtils.getUsername(), metaObject);
//                }
//            }
        } catch (Exception e) {
            throw new CustomException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            // todo 业务端没有username，需要改造后再使用
//            if (metaObject.hasGetter("updateBy")) {
//                if (metaObject.getValue("updateBy") == null) {
//                    this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
//                }
//            }
            if (metaObject.hasGetter("updateTime")) {
                if (metaObject.getValue("updateTime") == null) {
                    this.setFieldValByName("updateTime", new Date(), metaObject);
                }
            }
        } catch (Exception e) {
            throw new CustomException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }
}
