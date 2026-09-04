package com.omersemizoglu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoAddressIU;
import com.omersemizoglu.dto.response.DtoAddress;
import com.omersemizoglu.mapper.AddressMapper;
import com.omersemizoglu.model.Address;
import com.omersemizoglu.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressMapper addressMapper;

	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = addressMapper.toEntity(dtoAddressIU);
		address.setCreateTime(new Date());
		return address;
	}

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		return addressMapper.toDto(savedAddress);
	}
}
