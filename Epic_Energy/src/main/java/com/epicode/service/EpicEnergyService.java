package com.epicode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.EpicEnergy;
import com.epicode.repository.EpicEnergyRepository;

@Service
public class EpicEnergyService {

	@Autowired private EpicEnergyRepository repository;
	
//	public List<EpicEnergy> getEpicEnergy(){
//		return repository.findAll();
//	}
	
	public EpicEnergy getEpicEnergy () {
		if (!repository.existsById("IT000122345")) {
			//gestione eccezione
		}
		return repository.findById("IT000122345").get();
	}
	
	public EpicEnergy createEpicEnergy() {
		EpicEnergy epicEnergy = EpicEnergy.epicEnergy();
		if (repository.findById(epicEnergy.getVatNumber())!= null) {
			//gestione eccezione
		}
		return repository.save(epicEnergy);
	}
	
	public EpicEnergy updateEpicEnergy (EpicEnergy epicEnergy) {
		return repository.save(epicEnergy);
	}
	
}