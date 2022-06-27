package com.iten.PowerLegder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iten.controllers.VirtualPowerPlantController;
import com.iten.mapper.BatteryMapper;
import com.iten.model.Battery;
import com.iten.model.BatteryStatistics;
import com.iten.model.BatteryWrapper;
import com.iten.services.VirtualPowerPlantService;

@WebMvcTest (VirtualPowerPlantController.class)
class VirtualPowerPlantContollerTest {

	@MockBean
	private VirtualPowerPlantService service;
	
	@Autowired
    ObjectMapper mapper;
	
	@MockBean
	private BatteryStatistics statistics;
	
	@Autowired
	MockMvc mockMvc;
	
	Battery b1 = new Battery(123, "Battery1", 12200, 12.5);
	Battery b2 = new Battery(124, "Battery2", 12220, 52.2);
	Battery b3 = new Battery(125, "Battery3", 12240, 82.3);
	
	int fromPostCode = 12100;
	int toPostCode = 12300;
	
	@Test
	void getFilterBatteriesStatistics() throws Exception {
		List<Battery> batteries = Arrays.asList(b1, b2, b3);
		
		when(service.filteredBatteries(fromPostCode, toPostCode)).thenReturn(batteries);
		when(service.averageWattCapacity(batteries)).thenReturn(49.0);
		when(service.totalWattCapacity(batteries)).thenReturn(147.0);
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders
			.get("/batteries/statistics")
			.param("fromPostCode", Integer.toString(fromPostCode))
			.param("toPostCode", Integer.toString(toPostCode))
			.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(mockRequestBuilder)
			.andExpect(status().isOk());
	}
	
	@Test
	void saveBatteriesTest() throws Exception {
		List<Battery> batteries = Arrays.asList(b1, b2, b3);
		List<BatteryWrapper> wrapperBatteries = batteries.stream()
				.map(BatteryMapper::toBatteryWrapper)
				.collect(Collectors.toList());
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders
			.post("/batteries/saveBatteries")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(wrapperBatteries));
			
		mockMvc.perform(mockRequestBuilder)
			.andExpect(status().isCreated());
	}
}
