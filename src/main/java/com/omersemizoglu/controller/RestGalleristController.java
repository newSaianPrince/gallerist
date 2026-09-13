package com.omersemizoglu.controller;

import lombok.RequiredArgsConstructor;

import com.omersemizoglu.dto.response.RootEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.dto.response.DtoGallerist;
import com.omersemizoglu.dto.request.DtoGalleristIU;
import com.omersemizoglu.service.GalleristService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController {

	private final GalleristService galleristService;
	
	@PostMapping("/save")
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

}

