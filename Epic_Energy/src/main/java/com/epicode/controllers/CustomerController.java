package com.epicode.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
import com.epicode.repository.CustomerRepository;
import com.epicode.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService service;

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(service.getAllCustomers(), HttpStatus.OK);

	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<Page<Customer>> getAllCustomersPage(Pageable pageable) {
		Page<Customer> pageCustomers = service.getAllCustomersPage(pageable);
		return ResponseEntity.ok(pageCustomers);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
		return new ResponseEntity<Customer>(service.getCustomerById(id), HttpStatus.OK);

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
	
	@GetMapping("/customers")
	public Page<Customer> getFilteredCustomers(
	        @RequestParam(required = false) Double minIncome,
	        @RequestParam(required = false) Double maxIncome,
	        @RequestParam(required = false) LocalDate registrationStartDate,
	        @RequestParam(required = false) LocalDate registrationEndDate,
	        @RequestParam(required = false) LocalDate lastContactStartDate,
	        @RequestParam(required = false) LocalDate lastContactEndDate,
	        @RequestParam(required = false) String legalName,
	        Pageable pageable
	) {
	    Specification<Customer> spec = Specification.where(null);
	    	if (minIncome != null) {
	    	    if (maxIncome == null) {
	    	        maxIncome = Double.MAX_VALUE;
	    	    }
	    	    spec = spec.and(CustomerSpecifications.annualIncomeBetween(minIncome, maxIncome));
	    	}
	    	if (registrationStartDate != null) {
	    	    if (registrationEndDate == null) {
	    	        registrationEndDate = LocalDate.now();
	    	    }
	    	    spec = spec.and(CustomerSpecifications.registrationDateBetween(registrationStartDate, registrationEndDate));
	    	}
	    	if (lastContactStartDate != null) {
	    	    if (lastContactEndDate == null) {
	    	        lastContactEndDate = LocalDate.now();
	    	    }
	    	    spec = spec.and(CustomerSpecifications.lastContactDateBetween(lastContactStartDate, lastContactEndDate));
	    	}
	    if (legalName != null) {
	        spec = spec.and(CustomerSpecifications.legalNameContaining(legalName));
	    }
	    return CustomerRepository.findAll(spec, pageable);
	}

}
