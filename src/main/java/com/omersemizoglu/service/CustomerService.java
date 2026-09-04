package com.omersemizoglu.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoCustomerIU;
import com.omersemizoglu.dto.response.DtoCustomer;
import com.omersemizoglu.exception.BaseException;
import com.omersemizoglu.exception.ErrorMessage;
import com.omersemizoglu.exception.MessageType;
import com.omersemizoglu.mapper.CustomerMapper;
import com.omersemizoglu.model.Account;
import com.omersemizoglu.model.Address;
import com.omersemizoglu.model.Customer;
import com.omersemizoglu.repository.AccountRepository;
import com.omersemizoglu.repository.AddressRepository;
import com.omersemizoglu.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerMapper customerMapper;

	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		}

		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if (optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
		}

		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		customer.setFirstName(dtoCustomerIU.getFirstName());
		customer.setLastName(dtoCustomerIU.getLastName());
		customer.setTckn(dtoCustomerIU.getTckn());
		customer.setBirthOfDate(dtoCustomerIU.getBirthOfDate());
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());

		return customer;
	}

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		return customerMapper.toDto(savedCustomer);
	}
}
