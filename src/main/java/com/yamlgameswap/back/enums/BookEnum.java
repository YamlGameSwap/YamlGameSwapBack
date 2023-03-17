package com.yamlgameswap.back.enums;

public enum BookEnum {

    wordgame(1, "GameBook", "文字游戏"),

    wordbook(2, "WordBook", "文字剧本"),
    ;

    private final Integer bookCode;
    private final String zhBookType;
    private final String enBookType;

    BookEnum(Integer bookCode, String zhBookType, String enBookType) {
        this.bookCode = bookCode;
        this.zhBookType = zhBookType;
        this.enBookType = enBookType;
    }

    public Integer getBookCode() {
        return bookCode;
    }

    public String getEnBookType() {
        return enBookType;
    }

    public String getZhBookType() {
        return zhBookType;
    }
}
