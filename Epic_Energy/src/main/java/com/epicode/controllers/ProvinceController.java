package com.epicode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.models.Province;
import com.epicode.service.ProvinceService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/provinces")
public class ProvinceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ProvinceService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Province>> getAllProvinces (){
		return new ResponseEntity<List<Province>>(service.getAllProvinces(),HttpStatus.OK); 
			
	}
	@GetMapping("/{abv}")
	@ResponseBody
	public ResponseEntity<Province> getprovinceById (@PathVariable String abv){
	return new ResponseEntity<Province>(service.getProvinceById(abv),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	public Province createProvince(@RequestBody Province province) {
		return service.createProvince(province);
	}
	
	@PutMapping("/{abv}")
	@ResponseBody
	public Province updateProvince(@PathVariable String abv, @RequestBody Province province) {
		return service.updateProvince(abv, province);
	}
	
	@DeleteMapping("/{abv}")
	@ResponseBody
	public String deleteProvince(@PathVariable String abv) {
		return service.removeProvince(abv);
	}
	
}
