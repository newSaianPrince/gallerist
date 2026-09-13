package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.omersemizoglu.dto.request.DtoCarIU;
import com.omersemizoglu.dto.response.DtoCar;
import com.omersemizoglu.model.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
	DtoCar toDto(Car car);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createTime", ignore = true)
	Car toEntity(DtoCarIU dtoCarIU);
}