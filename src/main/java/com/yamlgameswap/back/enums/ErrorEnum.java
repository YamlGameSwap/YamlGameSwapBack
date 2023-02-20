package com.yamlgameswap.back.enums;

import java.util.HashMap;
import java.util.Map;

public enum ErrorEnum {
    ip_repeat_reginster(1, "ip_repeat_reginster", 429), // ip 重复
    user_data_error_reginster(2, "user_data_error_reginster", 300), // 用户数据格式错误 注册/修改用户数据的时候
    language_type_error(3, "language_type_error", 300),// 语言错误
    email_repeat_reginster(4, "email_repeat_reginster", 300),//邮箱重复
    password_error_login(5, "password_error_login", 300), // 密码错误
    email_no_exist_login(6, "email_no_exist_login", 300), // 没有该邮箱
    user_reginster_fail(7, "user_reginster_fail", 300), // 用户注册失败
    redis_is_null(8, "redis_is_null", 400),// redis 没有该字段
    ;

    private final Integer code;
    private final String value;
    private final Integer responseCode;
    private static final Map<Integer, String> codeMsg = new HashMap<>();
    private static final Map<Integer, Integer> codeResponseCode = new HashMap<>();

    static {
        ErrorEnum[] errorEnums = ErrorEnum.values();
        for (ErrorEnum errorEnum : errorEnums) {
            codeMsg.put(errorEnum.getCode(), errorEnum.getValue());
            codeResponseCode.put(errorEnum.getCode(), errorEnum.getResponseCode());
        }
    }


    ErrorEnum(Integer code, String value, Integer responseCode) {
        this.code = code;
        this.value = value;
        this.responseCode = responseCode;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public static Map<Integer, Integer> getCodeResponseMap() {
        return codeResponseCode;
    }

    public static Map<Integer, String> getCodeMsgMap() {
        return codeMsg;
    }

}
