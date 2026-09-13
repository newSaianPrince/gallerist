package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.omersemizoglu.dto.request.DtoAddressIU;
import com.omersemizoglu.dto.response.DtoAddress;
import com.omersemizoglu.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	DtoAddress toDto(Address address);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createTime", ignore = true)
	Address toEntity(DtoAddressIU dtoAddressIU);
}