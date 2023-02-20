package com.yamlgameswap.back.entity.except;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionException extends RuntimeException {

    private Integer errorCode;
    private String language;
    private Exception exception;

}