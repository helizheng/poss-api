package com.joenee.common.enums;

/**
 * Created by helizheng on 2017/7/15.
 */
public enum PossErrorEnum {
    _SYS_ERROR(-500,"服务器异常"),
    _PARAMS_ERROR(-9000,"参数错误"),
    _PARAMS_OK(200,"参数校验通过");;

    private final Integer code;
    private final String message;

    PossErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public String message() {
        return message;
    }

}
