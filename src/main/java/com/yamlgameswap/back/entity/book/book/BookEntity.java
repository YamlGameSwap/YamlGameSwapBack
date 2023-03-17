package com.yamlgameswap.back.entity.book.book;

import lombok.Data;

@Data
public class BookEntity {
    private Integer id;

    private String title;
    private String intro;
    private int chainId;
    private int bookType; // 小说，游戏本等
    private int contentType; // 内容类型 推理，恐怖，爱情


    // 媒体
    private String twitter;
    private String telegram;
    private String discord;
    private String website;

    private String tokenName;
    private String tokenContract;

    private String bookToken;
    private String createToken;

    private String BookImageUrl;
    private String BookBannerUrl;
}
