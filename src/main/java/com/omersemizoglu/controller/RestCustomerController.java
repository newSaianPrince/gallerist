package com.omersemizoglu.controller;

import lombok.RequiredArgsConstructor;

import com.omersemizoglu.dto.response.RootEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoCustomer;
import com.omersemizoglu.dto.request.DtoCustomerIU;
import com.omersemizoglu.service.CustomerService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController extends RestBaseController {

	private final CustomerService customerService;
	
	@PostMapping("/save")
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

}

