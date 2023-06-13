package com.ddkirill.ratesbot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table("users")
public class Users {

    @Id
    private Long telegramId;
    private String notificationTime;
    private String baseCurrency;
    private List<String> comparedCurrency = new ArrayList<>();
    @Version
    private Integer version;
    @Column(value = "user_id")
    private OldestRates oldestRates;

    public Users(Long telegramId, String notificationTime, String baseCurrency, List<String> comparedCurrency, OldestRates oldestRates) {
        this.telegramId = telegramId;
        this.notificationTime = notificationTime;
        this.baseCurrency = baseCurrency;
        this.comparedCurrency = comparedCurrency;
        this.oldestRates = oldestRates;
    }

    public Users() {
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public List<String> getComparedCurrency() {
        return comparedCurrency;
    }

    public void setComparedCurrency(String currencyAlias) {
        comparedCurrency.add(currencyAlias);
    }

    public OldestRates getOldestRates() {
        return oldestRates;
    }

    public void setOldestRates(OldestRates oldestRates) {
        this.oldestRates = oldestRates;
    }

    @Override
    public String toString() {
        return "Users{" +
                "telegramId=" + telegramId +
                ", notificationTime=" + notificationTime +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", currencyList=" + comparedCurrency +
                '}';
    }
}
