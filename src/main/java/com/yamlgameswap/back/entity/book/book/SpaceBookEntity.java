package com.yamlgameswap.back.entity.book.book;

import lombok.Data;

/**
 * 一本书可以投递多个 space
 */
@Data
public class SpaceBookEntity {
    private int id;
    private String bookToken;
    private String spaceToken;
}
