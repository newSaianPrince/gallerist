package com.omersemizoglu.controller;

import lombok.RequiredArgsConstructor;

import com.omersemizoglu.dto.response.RootEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.CurrencyRatesResponse;
import com.omersemizoglu.service.CurrencyRatesService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesController extends RestBaseController {

	private final CurrencyRatesService currencyRatesService;
	
	@GetMapping("/currency-rates")
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
	}

}

