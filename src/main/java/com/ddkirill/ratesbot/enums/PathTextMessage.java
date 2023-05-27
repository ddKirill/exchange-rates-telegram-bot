package com.ddkirill.ratesbot.enums;

public enum PathTextMessage {

    SELECT_BASE_CURRENCY("src/main/resources/text-messages/select_base_currency.txt"),
    SELECT_COMPARE_CURRENCY("src/main/resources/text-messages/select_compare_currency.txt"),
    SELECT_NOTIFICATION_TIME("src/main/resources/text-messages/select_notification_time.txt"),
    COMPLETED_SETTING("src/main/resources/text-messages/completed_setting.txt"),
    NON_COMMAND("src/main/resources/text-messages/non_command.txt"),
    MAIN_MENU("src/main/resources/text-messages/menu.txt"),
    ALL_CURRENCIES_LIST("src/main/resources/text-messages/all_currencies_list.txt"),
    CLEAR_COMPARE_CURRENCY("src/main/resources/text-messages/clear_currency.txt"),
    ARROW_TO_DOWN("src/main/resources/text-messages/arrow_to_down.txt");


    private String path;

    PathTextMessage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
