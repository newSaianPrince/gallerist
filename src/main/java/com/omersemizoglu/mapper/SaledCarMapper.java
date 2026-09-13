package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;

import com.omersemizoglu.dto.response.DtoSaledCar;
import com.omersemizoglu.model.SaledCar;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, GalleristMapper.class, CarMapper.class})
public interface SaledCarMapper {
	DtoSaledCar toDto(SaledCar saledCar);
}