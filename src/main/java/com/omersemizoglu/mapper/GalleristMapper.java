package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;

import com.omersemizoglu.dto.response.DtoGallerist;
import com.omersemizoglu.model.Gallerist;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface GalleristMapper {
	DtoGallerist toDto(Gallerist gallerist);
}