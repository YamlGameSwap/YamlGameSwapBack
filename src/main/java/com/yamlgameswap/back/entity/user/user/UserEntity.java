package com.yamlgameswap.back.entity.user.user;

import lombok.Data;

@Data
public class UserEntity {
    private Integer id;

    private String userAddress;
    private String userToken;
    private String password;
}
