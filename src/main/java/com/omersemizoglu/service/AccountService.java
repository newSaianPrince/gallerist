package com.omersemizoglu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoAccountIU;
import com.omersemizoglu.dto.response.DtoAccount;
import com.omersemizoglu.mapper.AccountMapper;
import com.omersemizoglu.model.Account;
import com.omersemizoglu.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountMapper accountMapper;

	private Account createAccount(DtoAccountIU dtoAccountIU) {
		Account account = accountMapper.toEntity(dtoAccountIU);
		account.setCreateTime(new Date());
		return account;
	}

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
		return accountMapper.toDto(savedAccount);
	}
}
