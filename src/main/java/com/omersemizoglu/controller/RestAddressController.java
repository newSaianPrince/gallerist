package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoAddress;
import com.omersemizoglu.dto.request.DtoAddressIU;
import com.omersemizoglu.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressController extends RestBaseController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/save")
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

}

