package com.ddkirill.ratesbot.enums;

public enum TextMessage {

    SELECT_BASE_CURRENCY("Предлагаю тебе выбрать свою базовую валюту, она будет отправной точкой для сравнения с другими валютами."),
    SELECT_COMPARE_CURRENCY("Ок, теперь выбери до 3х валют для сравнения с базовой."),
    SELECT_NOTIFICATION_TIME("Перейдем к выбору времени уведомления. Сейчас тебе нужно указать время, в которое RatesBot" +
            "будет отправлять тебе сообщение с курсами"),
    COMPLETED_SETTING("Настройка завершена! Теперь каждый день в указанное время ты будешь получать сообщение с выбранными курсами"),
    NON_COMMAND("Сообщение не является командой!");


    private String textMessage;

    TextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }
}
