package com.omersemizoglu.mapper;

import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.request.DtoAccountIU;
import com.omersemizoglu.dto.response.DtoAccount;
import com.omersemizoglu.model.Account;

@Component
public class AccountMapper {

	public DtoAccount toDto(Account account) {
		DtoAccount dto = new DtoAccount();
		dto.setId(account.getId());
		dto.setCreateTime(account.getCreateTime());
		dto.setAccountNo(account.getAccountNo());
		dto.setIban(account.getIban());
		dto.setAmount(account.getAmount());
		dto.setCurrencyType(account.getCurrencyType());
		return dto;
	}

	public Account toEntity(DtoAccountIU dto) {
		Account account = new Account();
		account.setAccountNo(dto.getAccountNo());
		account.setIban(dto.getIban());
		account.setAmount(dto.getAmount());
		account.setCurrencyType(dto.getCurrencyType());
		return account;
	}
}
