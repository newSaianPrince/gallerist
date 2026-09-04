package com.omersemizoglu.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.response.CurrencyRatesResponse;
import com.omersemizoglu.dto.request.DtoSaledCarIU;
import com.omersemizoglu.dto.response.DtoSaledCar;
import com.omersemizoglu.enums.CarStatusType;
import com.omersemizoglu.exception.BaseException;
import com.omersemizoglu.exception.ErrorMessage;
import com.omersemizoglu.exception.MessageType;
import com.omersemizoglu.mapper.SaledCarMapper;
import com.omersemizoglu.model.Car;
import com.omersemizoglu.model.Customer;
import com.omersemizoglu.model.SaledCar;
import com.omersemizoglu.repository.CarRepository;
import com.omersemizoglu.repository.CustomerRepository;
import com.omersemizoglu.repository.GalleristRepository;
import com.omersemizoglu.repository.SaledCarRepository;
import com.omersemizoglu.utils.DateUtils;

@Service
public class SaledCarService {
	
	@Autowired
	private SaledCarRepository saledCarRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CurrencyRatesService currencyRatesService;

	@Autowired
	private SaledCarMapper saledCarMapper;
	

	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService
				.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
		return customerUSDAmount;
	}
	
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}
	
	public BigDecimal remaningCustomerAmount(Customer customer , Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		return  remaningCustomerUSDAmount.multiply(usd);
	}

	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}

		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}

		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

		if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
				|| customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
			return true;
		}
		return false;
	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		
		if(!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
		
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		
		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		return saledCarMapper.toDto(savedSaledCar);
	}
}
