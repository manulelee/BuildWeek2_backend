package com.epicode.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.models.Customer;
import com.epicode.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired CustomerService service;

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Customer>> getAllCustomers (){
		return new ResponseEntity<List<Customer>>(service.getAllCustomers(),HttpStatus.OK); 
			
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<Page<Customer>> getAllCustomersPage (
	    @RequestParam Optional<Integer> page, 
	    @RequestParam Optional<Integer> size
	){
	    Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(20));  //Imposta dei valori predefiniti
	    Page<Customer> pageCustomers = service.getAllCustomersPage(pageable);
	    return ResponseEntity.ok(pageCustomers);
	}
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Customer> getCustomerById (@PathVariable String id){
	return new ResponseEntity<Customer>(service.getCustomerById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Customer createCustomer(@RequestBody Customer customer) {
		return service.createCustomer(customer);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
		return service.updateCustomer(id, customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCustomer(@PathVariable String id) {
		return service.removeCustomer(id);
	}
	
}
