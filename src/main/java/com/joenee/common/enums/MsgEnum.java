package com.joenee.common.enums;

/**
 * MsgEnum
 *
 * @author Li zheng
 * @description
 * @date 2016/8/15
 */
public enum MsgEnum {
    RAW_PASSWORD_ERROR("RAW_PASSWORD_ERROR","原始密码错误！"),
    NEWS_OLD_PASSWORD_NOTEQUAL("NEWS_OLD_PASSWORD_NOTEQUAL","新密码与确认密码不一致！"),
    USER_EXISTS("USER_EXISTS","用户已存在！"),;

    private MsgEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;

    private String description;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
