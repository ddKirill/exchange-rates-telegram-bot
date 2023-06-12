package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResponseBuilder {


    public String build(OpenExchangeRatesResponse openExchangeRatesResponse) {

        List<String> parseResponseRates = parseResponseRates(openExchangeRatesResponse);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("&#128202; <b>Ваши курсы:</b>");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("1 " + openExchangeRatesResponse.getBase() + " = " + parseResponseRates.get(0));
        stringBuilder.append("\n");
        stringBuilder.append("1 " + openExchangeRatesResponse.getBase() + " = " + parseResponseRates.get(1));
        stringBuilder.append("\n");
        stringBuilder.append("1 " + openExchangeRatesResponse.getBase() + " = " + parseResponseRates.get(2));

        return stringBuilder.toString();
    }

    private List<String> parseResponseRates(OpenExchangeRatesResponse openExchangeRatesResponse) {
        List<String> stringList = new ArrayList<>();
        Map<String, Double> rates = openExchangeRatesResponse.getRates();
        Set<String> stringSet = rates.keySet();
        Object[] strings = stringSet.toArray();
        Object firstString = strings[0];
        Object secondString = strings[1];
        Object thirdString = strings[2];
        Double firstValue = rates.get(firstString);
        Double secondValue = rates.get(secondString);
        Double thirdValue = rates.get(thirdString);
        String firstPair = firstValue.toString() + " " + firstString;
        String secondPair = secondValue.toString() + " " + secondString;
        String thirdPair = thirdValue.toString() + " " + thirdString;
        stringList.add(firstPair);
        stringList.add(secondPair);
        stringList.add(thirdPair);
        return stringList;
    }
}
