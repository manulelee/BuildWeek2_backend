package com.epicode.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.City;
import com.epicode.repository.CityRepository;

@Service
public class CityService {

	@Autowired private CityRepository repository;
	
	public List<City> getAllCities(){
		return repository.findAll();
	}
	
	public City getCityById (Long id) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		return repository.findById(id).get();
	}
	
	public City createCity(City city) {
		if (repository.findByCityNameAndProvince(city.getCityName(), city.getProvince())!= null) {
			//gestione eccezione
		}
		return repository.save(city);
	}
	
	public City updateCity (Long id, City city) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		return repository.save(city);
	}
	
	public String removeCity (Long id) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		repository.deleteById(id);
		return "City removed";
	}
	
}
