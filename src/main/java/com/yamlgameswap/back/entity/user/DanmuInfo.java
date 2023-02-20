package com.yamlgameswap.back.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DanmuInfo {

    @JsonProperty(value = "book_token")
    private String bookToken;

    @JsonProperty(value = "section_token")
    private String sectionToken;

    private String danmu;
}
