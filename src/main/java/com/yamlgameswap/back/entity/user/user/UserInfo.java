package com.yamlgameswap.back.entity.user.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfo {
    @JsonProperty(value = "address")
    private String UserAddress; // 钱包地址

    @JsonProperty(value = "user_token")
    private String UserToken; // 用户唯一识别 token

    private String password; // 密码
}
