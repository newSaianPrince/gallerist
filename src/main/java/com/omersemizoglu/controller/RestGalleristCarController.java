package com.omersemizoglu.controller;

import lombok.RequiredArgsConstructor;

import com.omersemizoglu.dto.response.RootEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoGalleristCar;
import com.omersemizoglu.dto.request.DtoGalleristCarIU;
import com.omersemizoglu.service.GalleristCarService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarController extends RestBaseController {

	private final GalleristCarService galleristCarService;
	
	@PostMapping("/save")
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}

