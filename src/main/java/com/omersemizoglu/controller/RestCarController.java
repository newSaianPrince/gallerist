package com.omersemizoglu.controller;

import lombok.RequiredArgsConstructor;

import com.omersemizoglu.dto.response.RootEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoCar;
import com.omersemizoglu.dto.request.DtoCarIU;
import com.omersemizoglu.service.CarService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/car")
public class RestCarController extends RestBaseController {

	private final CarService carService;
	
	@PostMapping("/save")
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

}

