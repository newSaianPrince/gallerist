package com.omersemizoglu.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.response.DtoCustomer;
import com.omersemizoglu.model.Customer;

@Component
public class CustomerMapper {

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private AccountMapper accountMapper;

	public DtoCustomer toDto(Customer customer) {
		DtoCustomer dto = new DtoCustomer();
		dto.setId(customer.getId());
		dto.setCreateTime(customer.getCreateTime());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setTckn(customer.getTckn());
		dto.setBirthOfDate(customer.getBirthOfDate());
		if (customer.getAddress() != null) {
			dto.setAddress(addressMapper.toDto(customer.getAddress()));
		}
		if (customer.getAccount() != null) {
			dto.setAccount(accountMapper.toDto(customer.getAccount()));
		}
		return dto;
	}
}
