package com.github.al.usercenter.model;

import com.github.al.common.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.function.Supplier;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/11 9:40
 * @Modified By:
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    private User user;

    /**
     * 角色信息
     */
    private String[] roles;

    /**
     * 菜单信息:
     * tb_resource -> code -> 菜单名 && type = 0
     */
    private String[] menus;

    /**
     * 权限信息：
     * tb_resource -> code -> 菜单名:按钮名 && type = 1
     */
    private String[] permissions;

    public UserInfo setRoles(Supplier<String[]> supplier) {
        this.roles = supplier.get();
        return this;
    }

    public UserInfo setMenus(Supplier<String[]> supplier) {
        this.menus = supplier.get();
        return this;
    }

    public UserInfo setPermissions(Supplier<String[]> supplier) {
        this.permissions = supplier.get();
        return this;
    }
}
