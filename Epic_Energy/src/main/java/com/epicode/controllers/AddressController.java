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

import com.epicode.models.Address;
import com.epicode.service.AddressService;



@RestController
@RequestMapping("/addresses")
public class AddressController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired AddressService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Address>> getAllAddresses (){
		return new ResponseEntity<List<Address>>(service.getAllAddress(),HttpStatus.OK); 
			
	}
	@GetMapping("/id")
	@ResponseBody
	public ResponseEntity<Address> getAddressById (Long id){
	return new ResponseEntity<Address>(service.getAddressById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	public Address createAddress(@RequestBody Address address) {
		return service.createAddress(address);
	}
	
	@PutMapping("/id")
	@ResponseBody
	public Address updateAddress(@RequestBody Address address) {
		return service.updateAddress(address.getId(), address);
	}
	
	@DeleteMapping("/id")
	@ResponseBody
	public String deleteAddress(Long id) {
		return service.removeAddress(id);
	}
	
}
