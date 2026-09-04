package com.omersemizoglu.dto.response;

import java.math.BigDecimal;

import com.omersemizoglu.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount extends DtoBase{

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
}
