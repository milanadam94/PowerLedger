package com.iten.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "batteries")
public class Battery {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer oid;
	
	@Column (name = "name")
	private String batteryName;
	
	@Column (name = "postcode")
	private Integer postCode;
	
	@Column (name = "capacity_watts")
	private Double wattCapacity;
}
