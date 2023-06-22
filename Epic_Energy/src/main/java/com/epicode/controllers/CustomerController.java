package com.epicode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.exceptions.CustomerNotFoundException;
import com.epicode.models.Customer;
import com.epicode.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService service;

	@GetMapping("/all")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(service.getAllCustomers(), HttpStatus.OK);

	}

	@GetMapping
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Customer>> getAllCustomersPage(Pageable pageable) {
		Page<Customer> pageCustomers = service.getAllCustomersPage(pageable);
		return ResponseEntity.ok(pageCustomers);
	}

	@GetMapping("/{id}")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getCustomerById(@PathVariable String id) {
		try {
			Customer customer = service.getCustomerById(id);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
		}

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
	public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
		// return service.updateCustomer(id, customer);
		try {
			Customer updatedCustomer = service.updateCustomer(id, customer);
			return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
		try {
			return new ResponseEntity<String>(
					service.removeCustomer(id), HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<String>(
					"Customer not found", HttpStatus.NOT_FOUND);
		}
	}

	// Exception handler for any other exceptions not handled by the above methods
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error("An error occurred: {}", e.getMessage());
		return new ResponseEntity<String>(
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
