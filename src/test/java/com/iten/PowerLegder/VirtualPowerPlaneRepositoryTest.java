package com.iten.PowerLegder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iten.model.Battery;
import com.iten.repository.VirtualPowerPlantRepository;

@SpringBootTest
class VirtualPowerPlaneRepositoryTest {

	@Autowired
	private VirtualPowerPlantRepository repository;
	
	Battery b1 = new Battery(123, "Battery1", 12200, 12.56);
	Battery b2 = new Battery(124, "Battery2", 12220, 52.26);
	Battery b3 = new Battery(125, "Battery3", 12240, 82.16);
	Battery b4 = new Battery(125, "Battery4", 12370, 82.16);
	
	
	@Test
	void validationSuccess() {
		List<Battery> batteries = Arrays.asList(b1, b2, b3, b4);
		repository.saveAll(batteries);
		
		List<Battery> batteriesIntoRange = repository.findByPostCodeBetweenOrderByBatteryNameAsc(12100, 12300);
		assertEquals(3, batteriesIntoRange.size());
	}
	
	@Test
	void validationReturnList() {
		List<Battery> batteries = Arrays.asList(b1, b2, b3, b4);
		List<Battery> savedBatteries = repository.saveAll(batteries);
		
		assertEquals(4, savedBatteries.size());
	}
}
