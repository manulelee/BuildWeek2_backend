package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.epicode.models.EpicEnergy;

public interface EpicEnergyRepository extends JpaRepository<EpicEnergy, String> {


}
