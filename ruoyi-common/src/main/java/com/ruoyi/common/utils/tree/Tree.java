package com.ruoyi.common.utils.tree;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 树状实体
 *
 * @author dengn
 */
@Data
@Accessors(chain = true)
public class Tree implements Serializable {

    private static final long serialVersionUID = 5491700581613599486L;

    /*********框架定义参数***********/
    private Long id;

    private Long pid;

    private String value;

    private String label;
    //是否禁用
    private boolean disabled = false;

    /**
     * 子项集合
     */
    private List<Tree> children;
    /*********框架定义参数***********/

    /**
     * 描述
     */
    private String remarks;
    /**
     * 排序
     */
    private Short orders;


}
