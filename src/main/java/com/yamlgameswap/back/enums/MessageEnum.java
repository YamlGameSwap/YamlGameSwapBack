package com.yamlgameswap.back.enums;

import java.util.HashMap;
import java.util.Map;

public enum MessageEnum {
    // 成功
    login_success(2001, "login_success", 2001), // 登录成功
    //异常
    user_login_fail(7, "user_reginster_fail", 300), // 用户登录失败
    user_data_error_reginster(2, "user_data_error_reginster", 300), // 用户数据格式错误 注册/修改用户数据的时候
    ip_repeat_reginster(1, "ip_repeat_reginster", 429), // ip 重复
    language_type_error(3, "language_type_error", 300),// 语言错误
    redis_is_null(8, "redis_is_null", 400),// redis 没有该字段
    token_is_null(10, "token_is_null", 3005),// user token 为空
    token_is_error(11, "token_is_error", 3006),//user token is error
    ;

    private final Integer code;
    private final String value;
    private final Integer responseCode;
    private static final Map<Integer, String> codeMsg = new HashMap<>();
    private static final Map<Integer, Integer> codeResponseCode = new HashMap<>();

    static {
        MessageEnum[] messageEnums = MessageEnum.values();
        for (MessageEnum messageEnum : messageEnums) {
            codeMsg.put(messageEnum.getCode(), messageEnum.getValue());
            codeResponseCode.put(messageEnum.getCode(), messageEnum.getResponseCode());
        }
    }


    MessageEnum(Integer code, String value, Integer responseCode) {
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
