package com.omersemizoglu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoCarIU;
import com.omersemizoglu.dto.response.DtoCar;
import com.omersemizoglu.mapper.CarMapper;
import com.omersemizoglu.model.Car;
import com.omersemizoglu.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarMapper carMapper;

	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = carMapper.toEntity(dtoCarIU);
		car.setCreateTime(new Date());
		return car;
	}

	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		Car savedCar = carRepository.save(createCar(dtoCarIU));
		return carMapper.toDto(savedCar);
	}
}
