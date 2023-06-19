package com.epicode.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.EpicEnergy;
import com.epicode.repository.EpicEnergyRepository;

@Service
public class EpicEnergyService {

	@Autowired private EpicEnergyRepository repository;
	
	public List<EpicEnergy> getEpicEnergy(){
		return repository.findAll();
	}
	
//	public EpicEnergy getEpicEnergyById (String vatNumber) {
//		if (!repository.existsById(vatNumber)) {
//			//gestione eccezione
//		}
//		return repository.findById(vatNumber).get();
//	}
	
	public EpicEnergy createEpicEnergy() {
		EpicEnergy epicEnergy = EpicEnergy.epicEnergy();
		return repository.save(epicEnergy);
	}
	
	public EpicEnergy updateEpicEnergy (EpicEnergy epicEnergy) {
		return repository.save(epicEnergy);
	}
	
}