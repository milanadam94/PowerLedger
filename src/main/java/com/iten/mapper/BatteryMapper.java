package com.iten.mapper;

import org.springframework.stereotype.Component;

import com.iten.model.Battery;
import com.iten.model.BatteryWrapper;

@Component
public class BatteryMapper {

	public Battery toBattery(BatteryWrapper battery) {
		return Battery.builder()
				.batteryName(battery.getBatteryName())
				.postCode(battery.getPostCode())
				.wattCapacity(battery.getWattCapacity())
				.build();
	}
	
	public BatteryWrapper toBatteryWrapper(Battery battery) {
		return BatteryWrapper.builder()
				.batteryName(battery.getBatteryName())
				.postCode(battery.getPostCode())
				.wattCapacity(battery.getWattCapacity())
				.build();
	}
}
