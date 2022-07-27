package com.ota.tour.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    vi("vi-VN"),
    en("en-US");

    private String code;

    public static Language findByCode(String code) {
        for(Language type: Language.values()) {
            if(type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
