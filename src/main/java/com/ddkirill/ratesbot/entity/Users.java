package com.ddkirill.ratesbot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Time;
import java.util.List;

@Table("users")
public class Users {

    @Id
    private Integer telegramId;
    private Time notificationTime;
    private String baseCurrency;
    private List<Currency> currencyList;
    @Version
    private Integer version;

    public Users(Integer telegramId, Time notificationTime, String baseCurrency, List<Currency> currencyList) {
        this.telegramId = telegramId;
        this.notificationTime = notificationTime;
        this.baseCurrency = baseCurrency;
        this.currencyList = currencyList;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public Time getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Time notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public String toString() {
        return "Users{" +
                "telegramId=" + telegramId +
                ", notificationTime=" + notificationTime +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", currencyList=" + currencyList +
                '}';
    }
}
