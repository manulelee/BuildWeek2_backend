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

import com.epicode.models.Address;
import com.epicode.service.AddressService;



@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired AddressService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Address>> getAllAddresses (){
		return new ResponseEntity<List<Address>>(service.getAllAddress(),HttpStatus.OK); 
			
	}
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Address> getAddressById (@PathVariable Long id){
	return new ResponseEntity<Address>(service.getAddressById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Address createAddress(@RequestBody Address address) {
		return service.createAddress(address);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
		return service.updateAddress(id, address);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteAddress(@PathVariable Long id) {
		return service.removeAddress(id);
	}
	
}
