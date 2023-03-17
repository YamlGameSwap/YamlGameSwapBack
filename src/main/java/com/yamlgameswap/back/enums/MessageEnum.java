package com.yamlgameswap.back.enums;

import java.util.HashMap;
import java.util.Map;

public enum MessageEnum {
    // 成功
    login_success(1, "login_success", 2001), // 登录成功
    spaces_get_success(1, "spaces_get", 2002),
    space_detail_get_success(1, "spaces_get", 2003),
    book_get_success(1, "book_get_success", 2004),
    book_detail_get_success(1, "book_detail_get_success", 2005),
    // 拦截器异常
    language_type_error(2, "language_type_error", 4002),// 语言错误
    redis_is_null(2, "redis_is_null", 4003),// redis 没有该字段
    ip_repeat_reginster(2, "ip_repeat_reginster", 4004), // ip 重复
    token_is_null(2, "token_is_null", 4005),// user token 为空
    token_is_error(2, "token_is_error", 4006),//user token is error
    // 逻辑异常
    // user
    // 登入
    user_login_fail(3, "user_reginster_fail", 3001), // 用户登录失败
    user_data_error_reginster(3, "user_data_error_reginster", 3002), // 用户数据格式错误 注册/修改用户数据的时候
    // space
    space_error(3, "space_error", 3003),
    space_detail_error(3, "space_detail_error", 3004),
    // book
    book_error(3, "book_error", 3005),
    book_detail_error(3, "book_detail_error", 3006),
    ;

    private final Integer typeCode;
    private final String value;
    private final Integer responseCode;
    private static final Map<Integer, String> responseCodeMsg = new HashMap<>();

    static {
        MessageEnum[] messageEnums = MessageEnum.values();
        for (MessageEnum messageEnum : messageEnums) {
            responseCodeMsg.put(messageEnum.getResponseCode(), messageEnum.getValue());
        }
    }


    MessageEnum(Integer typeCode, String value, Integer responseCode) {
        this.typeCode = typeCode;
        this.value = value;
        this.responseCode = responseCode;
    }

    public Integer getTypeCode() {
        return this.typeCode;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public static Map<Integer, String> getResponseCodeMsgMap() {
        return responseCodeMsg;
    }

}
