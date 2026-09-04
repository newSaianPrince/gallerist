package com.omersemizoglu.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.response.DtoGalleristCar;
import com.omersemizoglu.model.GalleristCar;

@Component
public class GalleristCarMapper {

	@Autowired
	private GalleristMapper galleristMapper;

	@Autowired
	private CarMapper carMapper;

	public DtoGalleristCar toDto(GalleristCar galleristCar) {
		DtoGalleristCar dto = new DtoGalleristCar();
		dto.setId(galleristCar.getId());
		dto.setCreateTime(galleristCar.getCreateTime());
		if (galleristCar.getGallerist() != null) {
			dto.setGallerist(galleristMapper.toDto(galleristCar.getGallerist()));
		}
		if (galleristCar.getCar() != null) {
			dto.setCar(carMapper.toDto(galleristCar.getCar()));
		}
		return dto;
	}
}
