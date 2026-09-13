package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;

import com.omersemizoglu.dto.response.DtoCustomer;
import com.omersemizoglu.model.Customer;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, AccountMapper.class})
public interface CustomerMapper {
	DtoCustomer toDto(Customer customer);
}