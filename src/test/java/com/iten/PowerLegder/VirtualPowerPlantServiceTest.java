package com.iten.PowerLegder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.iten.model.Battery;
import com.iten.repository.VirtualPowerPlantRepository;
import com.iten.services.VirtualPowerPlantService;

@SpringBootTest
class VirtualPowerPlantServiceTest {

	@Mock
	private VirtualPowerPlantRepository repository;
	
	@InjectMocks
	private VirtualPowerPlantService service;
	
	Battery b1 = new Battery(123, "Battery1", 12200, 12.56);
	Battery b2 = new Battery(124, "Battery2", 12220, 52.26);
	Battery b3 = new Battery(125, "Battery3", 12240, 82.16);
	Battery b4 = new Battery(125, "Battery4", 12370, 82.16);
	
	@Test
	void saveBatteriesIllegalStateException() {
		
		when(repository.saveAll(anyList())).thenReturn(new ArrayList<Battery>());
		assertThrows(IllegalStateException.class, () -> service.saveBatteries(new ArrayList<Battery>()));
	}
	
	@Test
	void saveBatteryValidationSuccess() {
		List<Battery> batteries = Arrays.asList(b1, b2, b3, b4);
		
		when(repository.saveAll(anyList())).thenReturn(batteries);
		assertEquals(4, batteries.size());
	}
	
	@Test
	void averageWattCapacityTest() {
		List<Battery> batteries = Arrays.asList(b1, b2, b3, b4);
		
		assertEquals(57.285, service.averageWattCapacity(batteries));
	}
	
	@Test
	void totalWattCapacityTest() {
		List<Battery> batteries = Arrays.asList(b1, b2, b3, b4);
		
		assertEquals(229.14, service.totalWattCapacity(batteries));
	}
}
