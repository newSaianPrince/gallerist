package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;

import com.omersemizoglu.dto.response.DtoGalleristCar;
import com.omersemizoglu.model.GalleristCar;

@Mapper(componentModel = "spring", uses = {GalleristMapper.class, CarMapper.class})
public interface GalleristCarMapper {
	DtoGalleristCar toDto(GalleristCar galleristCar);
}