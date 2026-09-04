package com.omersemizoglu.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesResponse {
	
	private Integer totalCount;
	
	private List<CurrencyRatesItems> items;
}
