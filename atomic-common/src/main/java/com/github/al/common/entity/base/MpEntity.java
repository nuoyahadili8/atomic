package com.github.al.common.entity.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 21:07
 * @Modified By:
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class MpEntity<T extends Model> extends Model<T> {
    @Override
    protected abstract Serializable pkVal();

    @TableField(value = "createAt",fill = FieldFill.INSERT_UPDATE)
    private String createAt;

    @TableField(value = "createDate",fill = FieldFill.INSERT_UPDATE)
    private Date createDate;

    @TableField(value = "modifyAt",fill = FieldFill.INSERT_UPDATE)
    private String modifyAt;

    @TableField(value = "modifyDate",fill = FieldFill.INSERT_UPDATE)
    private Date modifyDate;
}
