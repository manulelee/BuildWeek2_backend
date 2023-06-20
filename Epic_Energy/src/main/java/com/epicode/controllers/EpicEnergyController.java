package com.epicode.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.models.EpicEnergy;
import com.epicode.service.EpicEnergyService;


@RestController
@RequestMapping("/epic-energy")
public class EpicEnergyController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired EpicEnergyService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<EpicEnergy> getEpicEnergy() {
	return new ResponseEntity<EpicEnergy>(service.getEpicEnergy(),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	public EpicEnergy createEpicEnergy() {
		return service.createEpicEnergy();
	}
	
	@PutMapping("/id")
	@ResponseBody
	public EpicEnergy updateEpicEnergy(@RequestBody EpicEnergy epicEnergy) {
		return service.updateEpicEnergy(epicEnergy);
	}
	
}
