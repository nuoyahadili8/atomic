package com.github.al.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @Description: 部门信息 实体
 * @Date: create in 2018/3/10 21:21
 * @Modified By:
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_section")
public class Section extends MpEntity<Section> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    //部门编号
    private String code;
    //部门名称
    private String name;
    //优先级
    private Integer priority;
    //父级ID
    @TableField("parentId")
    private Long parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
