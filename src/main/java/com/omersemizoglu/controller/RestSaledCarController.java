package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoSaledCar;
import com.omersemizoglu.dto.request.DtoSaledCarIU;
import com.omersemizoglu.service.SaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarController extends RestBaseController {

	@Autowired
	private SaledCarService saledCarService;
	
	@PostMapping("/save")
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}

}

