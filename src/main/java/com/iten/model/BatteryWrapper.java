package com.iten.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BatteryWrapper {

	private String batteryName;
	
	private Integer postCode;
	
	private Double wattCapacity;
}
