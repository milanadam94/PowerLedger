package com.iten.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iten.mapper.BatteryMapper;
import com.iten.model.Battery;
import com.iten.model.BatteryStatistics;
import com.iten.model.BatteryWrapper;
import com.iten.services.VirtualPowerPlantService;

@RestController
@RequestMapping (value = "batteries")
public class VirtualPowerPlantController {

	@Autowired
	private VirtualPowerPlantService service;
	
	@GetMapping (value = "/statistics")
	public @ResponseBody BatteryStatistics getFilteredBatteriesStatistics(
			@RequestParam int fromPostCode,
			@RequestParam int toPostCode) {
		
		List<Battery> filteredBatteries = service.filteredBatteries(fromPostCode, toPostCode);
		BatteryStatistics statistics = new BatteryStatistics();

		statistics.setBatteries(filteredBatteries.stream()
				.map(BatteryMapper::toBatteryWrapper)
				.collect(Collectors.toList()));
		statistics.setAverageWattCapacity(service.averageWattCapacity(filteredBatteries));
		statistics.setTotalWattCapacity(service.totalWattCapacity(filteredBatteries));
		
		return statistics;
	}
	
	@PostMapping (value = "/saveBatteries")
	public ResponseEntity<List<BatteryWrapper>> saveBatteries(@Valid @RequestBody List<BatteryWrapper> batteries) {
		
		final List<Battery> battList = batteries.stream()
				.map(BatteryMapper::toBattery)
				.collect(Collectors.toList());
		
		final List<BatteryWrapper> battListWrapper = service.saveBatteries(battList).stream()
				.map(BatteryMapper::toBatteryWrapper)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(battListWrapper, HttpStatus.CREATED);
		
	}
	
}
