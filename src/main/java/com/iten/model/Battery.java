package com.iten.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Battery {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Oid;
	
	@Column
	private String batteryName;
	
	@Column
	private Integer postCode;
	
	@Column
	private Integer wattCapacity;
}
