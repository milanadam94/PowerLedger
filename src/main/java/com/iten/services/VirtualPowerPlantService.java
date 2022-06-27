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
	
	public List<Battery> saveBatteries(List<Battery> batteries) {
		if (!batteries.isEmpty()) {
			try {
				return repository.saveAll(batteries);
			} catch (NullPointerException e) {
				throw new NullPointerException("Attempted to save invalid data.");
			}
		} else {
			throw new IllegalStateException();
		}
	}
	
	public List<Battery> filteredBatteries(Integer fromPostCode, Integer toPostCode) {
		final List<Battery> filteredBatteries = repository.findByPostCodeBetweenOrderByBatteryNameAsc(fromPostCode, toPostCode);
		
		return filteredBatteries;
	}
	
	public double averageWattCapacity(List<Battery> batteries) {
		return batteries.stream()
				.mapToDouble(Battery::getWattCapacity)
				.average().getAsDouble();
	}
	
	public double totalWattCapacity(List<Battery> batteries) {
		return batteries.stream()
				.mapToDouble(Battery::getWattCapacity)
				.sum();
	}

}
