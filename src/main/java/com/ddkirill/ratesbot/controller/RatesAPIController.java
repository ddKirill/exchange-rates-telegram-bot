package com.ddkirill.ratesbot.controller;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;
import com.ddkirill.ratesbot.service.OpenExchangeClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatesAPIController {

    private OpenExchangeClient openExchangeClient;

    public RatesAPIController(OpenExchangeClient openExchangeClient) {
        this.openExchangeClient = openExchangeClient;
    }

    @GetMapping("/rates/{base}/{compare}")
    public OpenExchangeRatesResponse getRatesFor3Currency(@PathVariable List<String> compare,
                                                          @PathVariable String base) {
        OpenExchangeRatesResponse openExchangeRatesResponse = openExchangeClient.requestFor3Currency(base, compare);
        return openExchangeRatesResponse;
    }
}
