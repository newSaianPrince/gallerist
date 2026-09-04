package com.omersemizoglu.mapper;

import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.request.DtoAddressIU;
import com.omersemizoglu.dto.response.DtoAddress;
import com.omersemizoglu.model.Address;

@Component
public class AddressMapper {

	public DtoAddress toDto(Address address) {
		DtoAddress dto = new DtoAddress();
		dto.setId(address.getId());
		dto.setCreateTime(address.getCreateTime());
		dto.setCity(address.getCity());
		dto.setDistrict(address.getDistrict());
		dto.setNeighborhood(address.getNeighborhood());
		dto.setStreet(address.getStreet());
		return dto;
	}

	public Address toEntity(DtoAddressIU dto) {
		Address address = new Address();
		address.setCity(dto.getCity());
		address.setDistrict(dto.getDistrict());
		address.setNeighborhood(dto.getNeighborhood());
		address.setStreet(dto.getStreet());
		return address;
	}
}
