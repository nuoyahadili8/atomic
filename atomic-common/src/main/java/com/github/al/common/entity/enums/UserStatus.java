package com.github.al.common.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 21:11
 * @Modified By:
 */
public enum UserStatus implements IEnum {
    //用户状态(0启用,1禁用)
    ENABLED(0,"启用"),
    LOCKED(1,"禁用");

    private int code;
    private String name;
    UserStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
}
