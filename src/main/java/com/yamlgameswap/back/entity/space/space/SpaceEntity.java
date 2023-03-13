package com.yamlgameswap.back.entity.space.space;

import lombok.Data;

@Data
public class SpaceEntity {
    private Integer id;

    private String title;
    private String intro;


    // 媒体
    private String twitter;
    private String telegram;
    private String discord;
    private String website;

    private String tokenName;
    private String tokenContract;

    private String spaceToken;
    private String spaceImageUrl;
    private String spaceBannerUrl;
}
