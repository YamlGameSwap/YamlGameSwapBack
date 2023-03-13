package com.yamlgameswap.back.entity.space.space;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpaceResult {

    private String title;
    private String intro;


    // 媒体
    private String twitter;
    private String telegram;
    private String discord;
    private String website;

    private String token_name;
    private String token_contract;

    private String space_token;
    private int follow;
    private String space_image_url;
    private String space_banner_url;
}
