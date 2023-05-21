package com.ddkirill.ratesbot.enums;

public enum PathTextMessage {

    SELECT_BASE_CURRENCY("src/main/resources/text-messages/select_base_currency.txt"),
    SELECT_COMPARE_CURRENCY("src/main/resources/text-messages/select_compare_currency.txt"),
    SELECT_NOTIFICATION_TIME("src/main/resources/text-messages/select_notification_time.txt"),
    COMPLETED_SETTING("src/main/resources/text-messages/completed_setting.txt"),
    NON_COMMAND("src/main/resources/text-messages/non_command.txt");


    private String path;

    PathTextMessage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
