package com.epicode.service;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.Province;
import com.epicode.repository.ProvinceRepository;

@Service
public class ProvinceService {

	@Autowired private ProvinceRepository repository;
	
	public List<Province> getAllProvinces(){
		return repository.findAll();
	}
	
	public Province getProvinceById (String abv) {
		if (!repository.existsById(abv)) {
			//gestione eccezione
		}
		return repository.findById(abv).get();
	}
	
	public Province createProvince(Province province) {
		if (repository.findById(province.getAbbreviation())!= null) {
			//gestione eccezione
		}
		return repository.save(province);
	}
	
	public Province updateProvince (String abv, Province province) {
		if (!repository.existsById(abv)) {
			//gestione eccezione
		}
		return repository.save(province);
	}
	
	public String removeProvince (String abv) {
		if (!repository.existsById(abv)) {
			//gestione eccezione
		}
		repository.deleteById(abv);
		return "Province removed";
	}
	
	public Province getProvinceByName(String name) {
		Province p = repository.findByProvinceName(name).get();
		if (p==null) {
			//gestione eccezione
			System.out.println("la provincias non esiste");
		}
		return p;
	}
	
}
