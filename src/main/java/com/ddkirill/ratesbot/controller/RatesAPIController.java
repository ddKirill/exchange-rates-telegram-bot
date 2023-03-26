package com.ddkirill.ratesbot.controller;

import com.ddkirill.ratesbot.dto.RatesResponse;
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
    public RatesResponse getRatesFor3Currency(@PathVariable List<String> compare,
                                              @PathVariable String base) {
        RatesResponse ratesResponse = openExchangeClient.requestFor3Currency(base, compare);
        return ratesResponse;
    }
}
