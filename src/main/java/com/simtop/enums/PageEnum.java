package com.simtop.enums;

public enum PageEnum {
    LOGIN_HINT("登录提示",-1),
    INDEX("首页",0),
    TEST_ONE("测试页面一" , 1) ,
    TEST_TWO("测试页面二" , 2) ,
    TEST_THREE("测试页面三" , 3) ,
    TEST_FOUR("测试页面四" , 4) ,
    USER_LIST("用户列表" , 10) ,
    ROLE_LIST("角色列表" , 20) ,
    ACCESS_LIST("权限列表" , 30) ,
    NONE_ACCESS("无访问权限" , -9999);

    private String name;
    private int code;

    private PageEnum(String name , int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
