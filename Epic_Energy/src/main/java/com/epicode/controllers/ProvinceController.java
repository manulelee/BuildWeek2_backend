package com.epicode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.models.Province;
import com.epicode.service.ProvinceService;


@RestController
@RequestMapping("/provinces")
public class ProvinceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ProvinceService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Province>> getAllInvoices (){
		return new ResponseEntity<List<Province>>(service.getAllProvinces(),HttpStatus.OK); 
			
	}
	@GetMapping("/id")
	@ResponseBody
	public ResponseEntity<Province> getInvoiceById (String id){
	return new ResponseEntity<Province>(service.getProvinceById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	public Province createProvince(@RequestBody Province province) {
		return service.createProvince(province);
	}
	
	@PutMapping("/id")
	@ResponseBody
	public Province updateProvince(@RequestBody Province province) {
		return service.updateProvince(province.getProvinceName(), province);
	}
	
	@DeleteMapping("/id")
	@ResponseBody
	public String deleteProvince(String id) {
		return service.removeProvince(id);
	}
	
}
