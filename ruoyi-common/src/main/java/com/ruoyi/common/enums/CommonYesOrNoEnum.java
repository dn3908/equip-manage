package com.ruoyi.common.enums;

import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.compress.utils.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用是否枚举类
 */
public enum CommonYesOrNoEnum {
    NO(0, "0", "否"),
    YES(2, "2", "是");

    private Integer code;
    private String codeStr;
    private String name;

    CommonYesOrNoEnum(Integer code, String codeStr, String name) {
        this.code = code;
        this.codeStr = codeStr;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 将枚举转换成list格式
     * 这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法
     *
     * @return
     */
    static List getList() {
        List list = Lists.newArrayList();
        for (CommonYesOrNoEnum statusEnum : CommonYesOrNoEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("codeInt", statusEnum.getCode());
            map.put("codeStr", statusEnum.getCodeStr());
            map.put("name", statusEnum.getName());
            list.add(map);
        }
        return list;
    }

    static String getNameByCode(Integer code){
        String name = "";
        for (CommonYesOrNoEnum statusEnum : CommonYesOrNoEnum.values()) {
            if (statusEnum.getCode() == code){
                name = statusEnum.getName();
                continue;
            }
        }
        if (StringUtils.isNotEmpty(name)){
            return name;
        }else { // 找不到则返回 code
            return String.valueOf(code);
        }
    }

    static String getNameByCodeStr(String codeStr){
        String name = "";
        for (CommonYesOrNoEnum statusEnum : CommonYesOrNoEnum.values()) {
            if (statusEnum.getCodeStr().equals(codeStr)){
                name = statusEnum.getName();
                continue;
            }
        }
        if (StringUtils.isNotEmpty(name)){
            return name;
        }else { // 找不到则原样返回
            return codeStr;
        }
    }
}