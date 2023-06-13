package com.ddkirill.ratesbot.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(value = "oldest_rates")
public class OldestRates {

    @Column(value = "currency_alias")
    private List<String> currencyAlias = new ArrayList<>();
    @Column(value = "currency_rate")
    private List<Double> currencyRates = new ArrayList<>();
    @Column(value = "time_stamp")
    private Timestamp timestamp;

    public OldestRates(List<String> currencyAlias, List<Double> currencyRates,Timestamp timestamp) {
        this.currencyAlias = currencyAlias;
        this.currencyRates = currencyRates;
        this.timestamp = timestamp;
    }

    public List<String> getCurrencyAlias() {
        return currencyAlias;
    }

    public void setCurrencyAlias(List<String> currencyAlias) {
        this.currencyAlias = currencyAlias;
    }

    public List<Double> getCurrencyRates() {
        return currencyRates;
    }

    public void setCurrencyRates(List<Double> currencyRates) {
        this.currencyRates = currencyRates;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldestRates that = (OldestRates) o;
        return Objects.equals(currencyAlias, that.currencyAlias) && Objects.equals(currencyRates, that.currencyRates) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyAlias, currencyRates, timestamp);
    }

    @Override
    public String toString() {
        return "OldestRates{" +
                "currencyAlias=" + currencyAlias +
                ", currencyRates=" + currencyRates +
                ", timestamp=" + timestamp +
                '}';
    }
}
