package com.iten.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iten.model.Battery;

@Repository
public interface VirtualPowerPlantRepository extends JpaRepository<Battery, Integer>{
	
	List<Battery> findByPostCodeBetweenOrderByBatteryNameAsc(Integer from, Integer to);
}
