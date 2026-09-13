package com.omersemizoglu.controller;

import lombok.RequiredArgsConstructor;

import com.omersemizoglu.dto.response.RootEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoAccount;
import com.omersemizoglu.dto.request.DtoAccountIU;
import com.omersemizoglu.service.AccountService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController extends RestBaseController {

	private final AccountService accountService;
	
	@PostMapping("/save")
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}

}

