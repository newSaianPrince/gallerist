package com.omersemizoglu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoGalleristCar;
import com.omersemizoglu.dto.request.DtoGalleristCarIU;
import com.omersemizoglu.service.GalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarController extends RestBaseController {

	@Autowired
	private GalleristCarService galleristCarService;
	
	@PostMapping("/save")
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}

