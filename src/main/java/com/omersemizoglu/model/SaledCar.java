package com.omersemizoglu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "saled_car",
uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id" , "car_id" , "customer_id"},
name = "uq_gallerist_car_customer")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaledCar  extends BaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	private Gallerist gallerist;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
}
