package com.skycloud.common.enumcode;

/**
 * @author sky
 **/
public enum ResultCodeEnum {
    SUCCESS("SUCC", "成功"),

    FAILURE("FAIL", "失败");

    private String msg;

    private String code;

    private ResultCodeEnum(String code, String msg) { this.msg = msg;
        this.code = code;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}
