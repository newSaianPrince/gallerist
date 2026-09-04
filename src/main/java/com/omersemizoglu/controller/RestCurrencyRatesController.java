package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.CurrencyRatesResponse;
import com.omersemizoglu.service.CurrencyRatesService;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesController extends RestBaseController {

	@Autowired
	private CurrencyRatesService currencyRatesService;
	
	@GetMapping("/currency-rates")
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
	}

}

