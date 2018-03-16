package com.github.al.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.github.al.common.entity.base.MpEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author An
 * @Description: 角色信息 实体
 * @Date: create in 2018/3/10 21:20
 * @Modified By:
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_role")
public class Role extends MpEntity<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    //角色编码
    private String code;
    //角色名称
    private String name;
    //优先级
    private Integer priority;
    //描述
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
