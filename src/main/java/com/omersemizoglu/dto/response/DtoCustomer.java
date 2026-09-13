package com.omersemizoglu.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase{

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private LocalDate birthOfDate;
	
	private DtoAddress address;
	
	private DtoAccount  account;
}