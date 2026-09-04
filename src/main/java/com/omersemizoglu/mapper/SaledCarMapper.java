package com.omersemizoglu.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.response.DtoSaledCar;
import com.omersemizoglu.model.SaledCar;

@Component
public class SaledCarMapper {

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private GalleristMapper galleristMapper;

	@Autowired
	private CarMapper carMapper;

	public DtoSaledCar toDto(SaledCar saledCar) {
		DtoSaledCar dto = new DtoSaledCar();
		dto.setId(saledCar.getId());
		dto.setCreateTime(saledCar.getCreateTime());
		if (saledCar.getCustomer() != null) {
			dto.setCustomer(customerMapper.toDto(saledCar.getCustomer()));
		}
		if (saledCar.getGallerist() != null) {
			dto.setGallerist(galleristMapper.toDto(saledCar.getGallerist()));
		}
		if (saledCar.getCar() != null) {
			dto.setCar(carMapper.toDto(saledCar.getCar()));
		}
		return dto;
	}
}
