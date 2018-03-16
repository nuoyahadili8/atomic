package com.github.al.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.github.al.common.entity.base.MpEntity;
import com.github.al.common.entity.enums.UserStatus;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author An
 * @Description: 用户信息 实体
 * @Date: create in 2018/3/10 21:06
 * @Modified By:
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_user")
public class User extends MpEntity<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    //昵称
    private String nickname;
    //邮箱地址
    private String email;
    //用户名
    private String username;
    //密码
    private String password;
    //描述信息
    private String description;
    //用户状态(0启用,1禁用)
    private UserStatus status;
    //用户头像
    private String avatar;
    //所属部门
    private Long sectionId;

    // 用户角色,关联查询时使用
    private transient Set<Role> roleSet = Sets.newHashSet();


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
