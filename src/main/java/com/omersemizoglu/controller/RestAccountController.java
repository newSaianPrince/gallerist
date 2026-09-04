package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoAccount;
import com.omersemizoglu.dto.request.DtoAccountIU;
import com.omersemizoglu.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController extends RestBaseController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/save")
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}

}

