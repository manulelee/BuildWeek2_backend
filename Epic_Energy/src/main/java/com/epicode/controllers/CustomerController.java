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

import com.epicode.models.Customer;
import com.epicode.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired CustomerService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Customer>> getAllCustomers (){
		return new ResponseEntity<List<Customer>>(service.getAllCustomers(),HttpStatus.OK); 
			
	}
	@GetMapping("/id")
	@ResponseBody
	public ResponseEntity<Customer> getCustomerById (String id){
	return new ResponseEntity<Customer>(service.getCustomerById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	public Customer createCustomer(@RequestBody Customer customer) {
		return service.createCustomer(customer);
	}
	
	@PutMapping("/id")
	@ResponseBody
	public Customer updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer.getVatNumber(), customer);
	}
	
	@DeleteMapping("/id")
	@ResponseBody
	public String deleteCustomer(String id) {
		return service.removeCustomer(id);
	}
	
}
