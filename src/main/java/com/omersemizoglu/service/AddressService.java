package com.omersemizoglu.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoAddressIU;
import com.omersemizoglu.dto.response.DtoAddress;
import com.omersemizoglu.mapper.AddressMapper;
import com.omersemizoglu.model.Address;
import com.omersemizoglu.repository.AddressRepository;

@RequiredArgsConstructor
@Service
public class AddressService {

	private final AddressRepository addressRepository;

	private final AddressMapper addressMapper;

	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = addressMapper.toEntity(dtoAddressIU);
		address.setCreateTime(LocalDateTime.now());
		return address;
	}

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		return addressMapper.toDto(savedAddress);
	}
}
