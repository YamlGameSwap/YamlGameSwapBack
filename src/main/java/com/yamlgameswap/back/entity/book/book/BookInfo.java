package com.yamlgameswap.back.entity.book.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookInfo {
    @JsonProperty(value = "page")
    private String page;

    @JsonProperty(value = "space_token")
    private String spaceToken;

    @JsonProperty(value = "user_token")
    private String userToken;
}
