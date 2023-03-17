package com.yamlgameswap.back.entity.book.book;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResult {

    private String address;

    private String title;
    private String intro;


    // 媒体
    private String twitter;
    private String telegram;
    private String discord;
    private String website;

    private String token_name;
    private String token_contract;

    private int follow;
    private int like;
    private String book_token;
    private String book_image_url;
    private String book_banner_url;
}
