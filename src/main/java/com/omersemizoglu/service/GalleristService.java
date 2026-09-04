package com.omersemizoglu.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.request.DtoGalleristIU;
import com.omersemizoglu.dto.response.DtoGallerist;
import com.omersemizoglu.exception.BaseException;
import com.omersemizoglu.exception.ErrorMessage;
import com.omersemizoglu.exception.MessageType;
import com.omersemizoglu.mapper.GalleristMapper;
import com.omersemizoglu.model.Address;
import com.omersemizoglu.model.Gallerist;
import com.omersemizoglu.repository.AddressRepository;
import com.omersemizoglu.repository.GalleristRepository;

@Service
public class GalleristService {

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private GalleristMapper galleristMapper;

	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		}

		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date());
		gallerist.setFirstName(dtoGalleristIU.getFirstName());
		gallerist.setLastName(dtoGalleristIU.getLastName());
		gallerist.setAddress(optAddress.get());

		return gallerist;
	}

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
		return galleristMapper.toDto(savedGallerist);
	}

}
