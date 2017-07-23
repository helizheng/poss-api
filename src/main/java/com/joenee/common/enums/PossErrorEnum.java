package com.joenee.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by helizheng on 2017/7/15.
 */
public enum PossErrorEnum {
    REQUEST_EXCEPTION("REQUEST_EXCEPTION", "请求异常"),
    HANDLE_EXCEPTION("HANDLE_EXCEPTION", "处理异常");

    private final String code;
    private final String message;

    private PossErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (PossErrorEnum type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }
}
