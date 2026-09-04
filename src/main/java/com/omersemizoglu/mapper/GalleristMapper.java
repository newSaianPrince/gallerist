package com.omersemizoglu.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.response.DtoGallerist;
import com.omersemizoglu.model.Gallerist;

@Component
public class GalleristMapper {

	@Autowired
	private AddressMapper addressMapper;

	public DtoGallerist toDto(Gallerist gallerist) {
		DtoGallerist dto = new DtoGallerist();
		dto.setId(gallerist.getId());
		dto.setCreateTime(gallerist.getCreateTime());
		dto.setFirstName(gallerist.getFirstName());
		dto.setLastName(gallerist.getLastName());
		if (gallerist.getAddress() != null) {
			dto.setAddress(addressMapper.toDto(gallerist.getAddress()));
		}
		return dto;
	}
}
