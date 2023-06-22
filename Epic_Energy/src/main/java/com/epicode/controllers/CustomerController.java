package com.epicode.controllers;

import java.time.LocalDate;
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

	@Autowired
	CustomerService service;

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

	/**
	 * Retrieves a page of customers that match the provided filter criteria.
	 *
	 * @param  minAnnualIncome        (optional) minimum annual income of customers to retrieve
	 * @param  maxAnnualIncome        (optional) maximum annual income of customers to retrieve
	 * @param  minRegistrationDate    (optional) minimum registration date of customers to retrieve
	 * @param  maxRegistrationDate    (optional) maximum registration date of customers to retrieve
	 * @param  minLastContactDate     (optional) minimum last contact date of customers to retrieve
	 * @param  maxLastContactDate     (optional) maximum last contact date of customers to retrieve
	 * @param  name                   (optional) legal name of customers to retrieve
	 * @param  pageable               page request params
	 * @return                        page of customers that match the provided filter criteria
	 */
	@GetMapping("/filter")
	@ResponseBody
	public Page<Customer> filterCustomersIncome(@RequestParam(required = false) Double minAnnualIncome,
			@RequestParam(required = false) Double maxAnnualIncome,
			@RequestParam(required = false) LocalDate minRegistrationDate,
			@RequestParam(required = false) LocalDate maxRegistrationDate,
			@RequestParam(required = false) LocalDate minLastContactDate,
			@RequestParam(required = false) LocalDate maxLastContactDate, @RequestParam(required = false) String name,
			Pageable pageable) {
		if (minAnnualIncome == null & maxAnnualIncome == null & minRegistrationDate == null
				& maxRegistrationDate == null & minLastContactDate == null & maxLastContactDate == null) {
			return service.getAllCustomersPage(pageable);
		} else {
			if (name != null) {
				return service.getCustomersByLegalNameContaining(name, pageable);
			}
			if (minAnnualIncome != null || maxAnnualIncome != null) {
				if (minAnnualIncome == null) {
					minAnnualIncome = 0.0;
				}
				if (maxAnnualIncome == null) {
					maxAnnualIncome = Double.MAX_VALUE;
				}
				return service.getCustomersByAnnualIncomeRange(minAnnualIncome, maxAnnualIncome, pageable);
			}
			if (minRegistrationDate != null || maxAnnualIncome != null) {
				if (maxRegistrationDate == null) {
					maxRegistrationDate = LocalDate.now();
				}
				if (minRegistrationDate == null) {
					minRegistrationDate = LocalDate.MIN;
				}
				return service.getCustomersByRegistrationDateRange(minRegistrationDate, maxRegistrationDate, pageable);
			}
			if (minLastContactDate != null) {
				if (maxLastContactDate == null) {
					maxLastContactDate = LocalDate.now();
				}
				return service.getCustomersByLastContactDateRange(minLastContactDate, maxLastContactDate, pageable);
			}

		}
		return service.getAllCustomersPage(pageable);

	}
}
