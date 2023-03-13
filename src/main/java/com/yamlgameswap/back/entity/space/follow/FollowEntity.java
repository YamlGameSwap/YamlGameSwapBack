package com.yamlgameswap.back.entity.space.follow;

import lombok.Data;

@Data
public class FollowEntity {
    private int id;
    private String spaceToken;
    private String userToken;
}
