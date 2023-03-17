package com.yamlgameswap.back.entity.space.follow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpaceFollowInfo {
    @JsonProperty(value = "spaceToken")
    private String spaceToken;
    @JsonProperty(value = "userToken")
    private String userToken;
}
