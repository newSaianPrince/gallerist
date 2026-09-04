package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoCustomer;
import com.omersemizoglu.dto.request.DtoCustomerIU;
import com.omersemizoglu.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController extends RestBaseController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

}

