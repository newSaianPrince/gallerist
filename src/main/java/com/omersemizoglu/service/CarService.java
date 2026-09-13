package com.omersemizoglu.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoCarIU;
import com.omersemizoglu.dto.response.DtoCar;
import com.omersemizoglu.mapper.CarMapper;
import com.omersemizoglu.model.Car;
import com.omersemizoglu.repository.CarRepository;

@RequiredArgsConstructor
@Service
public class CarService {

	private final CarRepository carRepository;

	private final CarMapper carMapper;

	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = carMapper.toEntity(dtoCarIU);
		car.setCreateTime(LocalDateTime.now());
		return car;
	}

	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		Car savedCar = carRepository.save(createCar(dtoCarIU));
		return carMapper.toDto(savedCar);
	}
}
