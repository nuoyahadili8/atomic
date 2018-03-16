package com.github.al.common.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 21:13
 * @Modified By:
 */
public enum ResourceType implements IEnum {
    //类型(0菜单,1按钮)
    MENU(0, "菜单"),
    BUTTON(1, "按钮"),
    OTHER(2, "其他");

    private int code;
    private String name;
    ResourceType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
}
