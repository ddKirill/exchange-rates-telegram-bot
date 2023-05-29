package com.ddkirill.ratesbot.enums;

public enum CurrencyAliasAndTitle {

    USD("USA Dollar"),
    EUR("Euro"),
    RUB("Russian Ruble"),
    AED("United Arab Emirates Dirham"),
    UZS("Uzbekistan Som"),
    UAH("Ukrainian Hryvnia"),
    TRY("Turkish Lira"),
    TJS("Tajikistani Somoni"),
    KZT("Kazakhstani Tenge"),
    KRW("South Korean Won"),
    JPY("Japanese Yen"),
    INR("Indian Rupee"),
    BTC("BitCoin"),
    AZN("Azerbaijani Manat"),
    GEL("Georgian Lari"),
    PLN("Polish Zloty"),
    MXN("Mexican Peso"),
    MNT("Mongolian Tugrik"),
    COP("Colombian Peso"),
    CAD("Canadian Dollar");



    private final String currencyTitle;

    CurrencyAliasAndTitle(String currencyTitle) {
        this.currencyTitle = currencyTitle;
    }

    public String getCurrencyTitle() {
        return currencyTitle;
    }

    public static boolean contains(String alias) {
        for (CurrencyAliasAndTitle title : values()) {
            if (title.name().equals(alias)) {
                return true;
            }
        }
        return false;
    }
}
