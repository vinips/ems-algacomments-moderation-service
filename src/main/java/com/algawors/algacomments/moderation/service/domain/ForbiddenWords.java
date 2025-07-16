package com.algawors.algacomments.moderation.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ForbiddenWords {

    ODIO("ódio"),
    XINGAMENTO("xingamento");

    private String value;

}
