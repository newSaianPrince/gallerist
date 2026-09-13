package com.omersemizoglu.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoAccountIU;
import com.omersemizoglu.dto.response.DtoAccount;
import com.omersemizoglu.mapper.AccountMapper;
import com.omersemizoglu.model.Account;
import com.omersemizoglu.repository.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	private final AccountMapper accountMapper;

	private Account createAccount(DtoAccountIU dtoAccountIU) {
		Account account = accountMapper.toEntity(dtoAccountIU);
		account.setCreateTime(LocalDateTime.now());
		return account;
	}

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
		return accountMapper.toDto(savedAccount);
	}
}
