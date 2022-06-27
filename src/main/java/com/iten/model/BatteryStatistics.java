package com.iten.model;

import java.util.List;

import lombok.Data;

@Data
public class BatteryStatistics {
	
	private List<BatteryWrapper> batteries;
	
	private double totalWattCapacity;
	
	private double averageWattCapacity;

}
