package com.yamlgameswap.back.enums;

public enum StatusEnum {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ERROR("ERROR"),
    ;

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
