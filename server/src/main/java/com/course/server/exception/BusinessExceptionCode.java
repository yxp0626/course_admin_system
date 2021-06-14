package com.course.server.exception;

/**
 * @author Xiaoping Yu
 * @date 2021/6/14 - 14:47
 */

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
