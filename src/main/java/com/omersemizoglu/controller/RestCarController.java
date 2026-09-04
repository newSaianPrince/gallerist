package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoCar;
import com.omersemizoglu.dto.request.DtoCarIU;
import com.omersemizoglu.service.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarController extends RestBaseController {

	@Autowired
	private CarService carService;
	
	@PostMapping("/save")
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

}

