package com.github.al.common.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 21:14
 * @Modified By:
 */
public enum ResourceMethod implements IEnum {
    //请求方式,GET/POST/DELETE/PUT,NONE(无)
    GET,POST,DELETE,PUT,NONE;

    @Override
    public Serializable getValue() {
        return this.toString();
    }
}
