package com.yamlgameswap.back.entity.user.danmu;

import lombok.Data;

@Data
public class DanmuEntity {
    private int id;

    private String userToken;
    private String bookToken;
    private String sectionToken;
    private String comment;
}
