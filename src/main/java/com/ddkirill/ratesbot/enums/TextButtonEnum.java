package com.ddkirill.ratesbot.enums;

public enum TextButtonEnum {

    MAIN_MENU("Главное меню");

    private String textButton;

    TextButtonEnum(String textButton) {
        this.textButton = textButton;
    }

    public String getTextButton() {
        return textButton;
    }
}
