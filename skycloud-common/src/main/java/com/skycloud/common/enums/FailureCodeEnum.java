package com.skycloud.common.enums;

import java.util.Arrays;

/**
 * @author sky
 **/
public enum FailureCodeEnum {

    SERVICE_EXCEPTION("SERVICE_EXCEPTION", "服务异常");


    private String msg;
    private String code;

    FailureCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static FailureCodeEnum getByCode(String code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(SERVICE_EXCEPTION);
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}