package com.yamlgameswap.back.entity.book.like;

import lombok.Data;

@Data
public class BookLikeEntity {
    private Integer id;
    private String bookToken;
    private String userToken;
}
