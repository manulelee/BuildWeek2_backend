package com.epicode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.models.City;
import com.epicode.service.CityService;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired CityService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<City>> getAllCities (){
		return new ResponseEntity<List<City>>(service.getAllCities(),HttpStatus.OK); 
			
	}
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<City> getCityById (@PathVariable Long id){
	return new ResponseEntity<City>(service.getCityById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public City createCity(@RequestBody City city) {
		return service.createCity(city);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public City updateCity(@PathVariable Long id, @RequestBody City city) {
		return service.updateCity(id, city);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCity(@PathVariable Long id) {
		return service.removeCity(id);
	}
	
}
