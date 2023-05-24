package com.ddkirill.ratesbot.enums;

public enum CountryFlagEnum {

    USD("&#x1F1FA;&#x1F1F8;"),
    EUR("&#x1F1EA;&#x1F1FA;"),
    RUB("&#x1F1F7;&#x1F1FA;"),
    AED("&#x1F1E6;&#x1F1EA;"),
    UZS("&#x1F1FA;&#x1F1FF;"),
    UAH("&#x1F1FA;&#x1F1E6;"),
    TRY("&#x1F1F9;&#x1F1F7;"),
    TJS("&#x1F1F9;&#x1F1EF;"),
    KZT("&#x1F1F0;&#x1F1FF;"),
    KRW("&#x1F1F0;&#x1F1F7;"),
    JPY("&#x1F1EF;&#x1F1F5;"),
    INR("&#x1F1EE;&#x1F1F3;"),
    BTC(""),
    AZN("&#x1F1E6;&#x1F1FF;"),
    GEL("&#x1F1EC;&#x1F1EA;"),
    PLN("&#x1F1F5;&#x1F1F1;"),
    MXN("&#x1F1F2;&#x1F1FD;"),
    MNT("&#x1F1F2;&#x1F1F3;"),
    COP("&#x1F1E8;&#x1F1F4;"),
    CAD("&#x1F1E8;&#x1F1E6;");

    private final String icon;

    CountryFlagEnum(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
