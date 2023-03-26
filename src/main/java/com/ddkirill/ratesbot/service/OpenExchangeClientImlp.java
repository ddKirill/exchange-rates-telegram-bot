package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.RatesResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenExchangeClientImlp implements OpenExchangeClient {

    private final URLBuilder urlBuilder;

    public OpenExchangeClientImlp(URLBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public RatesResponse requestFor3Currency(String baseCurrency, List<String> compareCurrency) {
        String uri = urlBuilder.buildOpenExchangeURL(baseCurrency, compareCurrency);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, RatesResponse.class);
    }
}
