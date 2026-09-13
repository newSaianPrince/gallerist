package com.omersemizoglu.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomerIU {

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String tckn;
	
	@NotNull
	private LocalDate birthOfDate;
	
	@NotNull
	private Long addressId;
	
	@NotNull
	private Long  accountId;
}