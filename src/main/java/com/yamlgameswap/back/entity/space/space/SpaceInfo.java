package com.yamlgameswap.back.entity.space.space;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpaceInfo {
    @JsonProperty(value = "page")
    private String page;

    @JsonProperty(value = "space_token")
    private String spaceToken;

    @JsonProperty(value = "user_token")
    private String userToken;
}
