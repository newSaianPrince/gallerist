package com.omersemizoglu.mapper;

import org.springframework.stereotype.Component;

import com.omersemizoglu.dto.request.DtoCarIU;
import com.omersemizoglu.dto.response.DtoCar;
import com.omersemizoglu.model.Car;

@Component
public class CarMapper {

	public DtoCar toDto(Car car) {
		DtoCar dto = new DtoCar();
		dto.setId(car.getId());
		dto.setCreateTime(car.getCreateTime());
		dto.setPlaka(car.getPlaka());
		dto.setBrand(car.getBrand());
		dto.setModel(car.getModel());
		dto.setProductionYear(car.getProductionYear());
		dto.setPrice(car.getPrice());
		dto.setCurrencyType(car.getCurrencyType());
		dto.setDamagePrice(car.getDamagePrice());
		dto.setCarStatusType(car.getCarStatusType());
		return dto;
	}

	public Car toEntity(DtoCarIU dto) {
		Car car = new Car();
		car.setPlaka(dto.getPlaka());
		car.setBrand(dto.getBrand());
		car.setModel(dto.getModel());
		car.setProductionYear(dto.getProductionYear());
		car.setPrice(dto.getPrice());
		car.setCurrencyType(dto.getCurrencyType());
		car.setDamagePrice(dto.getDamagePrice());
		car.setCarStatusType(dto.getCarStatusType());
		return car;
	}
}
