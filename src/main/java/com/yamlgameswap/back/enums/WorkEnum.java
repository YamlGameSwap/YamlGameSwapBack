package com.yamlgameswap.back.enums;

public enum WorkEnum {
    NULLSERVICE(0),
    USERSERVICE(1),
    SPACESERVICE(2),
    ;

    private final Integer service;

    WorkEnum(Integer service) {
        this.service = service;
    }

    public Integer getService() {
        return this.service;
    }
}
