package com.github.al.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.github.al.common.entity.base.MpEntity;
import com.github.al.common.entity.enums.ResourceMethod;
import com.github.al.common.entity.enums.ResourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author An
 * @Description: 资源信息(菜单&按钮) 实体
 * @Date: create in 2018/3/10 21:15
 * @Modified By:
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_resource")
public class Resource extends MpEntity<Resource> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //资源编码(规则->菜单名:按钮名 || 菜单名)
    private String code;
    //资源名
    private String name;
    //类型(0菜单,1按钮,2其他)
    private ResourceType type;
    //服务端地址
    private String url;
    //请求方式,GET/POST/DELETE/PUT,NONE(无)
    private ResourceMethod method;
    //优先级(仅作用于当前级别)
    private Integer priority;
    //前端URL
    private String path;
    //Vue组件地址
    private String component;
    //图标
    private String icon;
    //父级ID
    @TableField("parentId")
    private Long parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}