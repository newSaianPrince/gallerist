package com.omersemizoglu.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoGalleristCarIU;
import com.omersemizoglu.dto.response.DtoGalleristCar;
import com.omersemizoglu.exception.BaseException;
import com.omersemizoglu.exception.ErrorMessage;
import com.omersemizoglu.exception.MessageType;
import com.omersemizoglu.mapper.GalleristCarMapper;
import com.omersemizoglu.model.Car;
import com.omersemizoglu.model.Gallerist;
import com.omersemizoglu.model.GalleristCar;
import com.omersemizoglu.repository.CarRepository;
import com.omersemizoglu.repository.GalleristCarRepository;
import com.omersemizoglu.repository.GalleristRepository;

@Service
public class GalleristCarService {

	@Autowired
	private GalleristCarRepository galleristCarRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private GalleristCarMapper galleristCarMapper;

	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if (optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
		}

		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		}

		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());

		return galleristCar;
	}

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		return galleristCarMapper.toDto(savedGalleristCar);
	}
}
