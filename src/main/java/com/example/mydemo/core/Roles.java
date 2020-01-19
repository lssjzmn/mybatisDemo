package com.example.mydemo.core;

public enum Roles {

    SUPERADMIN("S", "super_administrator"), ADMIN("A", "administrator"), USER("U", "normal_user");

    Roles(String roleName, String roleDesc) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    private String roleName;
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }
}
