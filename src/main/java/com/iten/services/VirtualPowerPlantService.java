package com.iten.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iten.model.Battery;
import com.iten.repository.VirtualPowerPlantRepository;

@Service
public class VirtualPowerPlantService {

	@Autowired
	private VirtualPowerPlantRepository repository;
	
	public void saveBatteries(List<Battery> batteries) {
		repository.saveAll(batteries);
	}
	
	public List<Battery> filteredBatteries(Integer fromPostCode, Integer toPostCode) {
		final List<Battery> filteredBatteries = repository.findBatteriesByPostcode(fromPostCode, toPostCode);
		
		return filteredBatteries;
	}
	
	public double averageWattCapacity(List<Battery> batteries) {
		return batteries.stream()
				.mapToDouble(b -> b.getWattCapacity())
				.average().getAsDouble();
	}
	
	public double totalWattCapacity(List<Battery> batteries) {
		return batteries.stream()
				.mapToDouble(b -> b.getWattCapacity())
				.sum();
	}
	
}
