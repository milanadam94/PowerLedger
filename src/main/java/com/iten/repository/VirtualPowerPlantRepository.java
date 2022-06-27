package com.iten.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iten.model.Battery;

@Repository
public interface VirtualPowerPlantRepository extends JpaRepository<Battery, Integer>{

	List<com.iten.model.Battery> findBatteriesByPostcode(@Param("from") Integer fromPostCode, @Param("to") Integer toPostCode);
}
