package com.yamlgameswap.back.entity.book.follow;

import lombok.Data;

@Data
public class BookFollowEntity {
    private Integer id;
    private String bookToken;
    private String userToken;
}
