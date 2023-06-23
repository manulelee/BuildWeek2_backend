package com.epicode.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.exceptions.CustomerNotFoundException;
import com.epicode.models.Customer;
import com.epicode.service.CustomerService;

@CrossOrigin(maxAge = 30)
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService service;

	@CrossOrigin
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

	/**
	 * Retrieves a page of customers that match the provided filter criteria.
	 *
	 * @param minAnnualIncome     (optional) minimum annual income of customers to
	 *                            retrieve
	 * @param maxAnnualIncome     (optional) maximum annual income of customers to
	 *                            retrieve
	 * @param minRegistrationDate (optional) minimum registration date of customers
	 *                            to retrieve
	 * @param maxRegistrationDate (optional) maximum registration date of customers
	 *                            to retrieve
	 * @param minLastContactDate  (optional) minimum last contact date of customers
	 *                            to retrieve
	 * @param maxLastContactDate  (optional) maximum last contact date of customers
	 *                            to retrieve
	 * @param name                (optional) legal name of customers to retrieve
	 * @param pageable            page request params
	 * @return page of customers that match the provided filter criteria
	 */

	/**
	 * Filters customers by the given query parameters, or returns all customers if no
	 * parameters are provided.
	 *
	 * @param  minAnnualIncome       the minimum annual income to filter by (optional).
	 * @param  maxAnnualIncome       the maximum annual income to filter by (optional).
	 * @param  minRegistrationDate   the minimum registration date to filter by (optional).
	 * @param  maxRegistrationDate   the maximum registration date to filter by (optional).
	 * @param  minLastContactDate    the minimum last contact date to filter by (optional).
	 * @param  maxLastContactDate    the maximum last contact date to filter by (optional).
	 * @param  legalName             the legal name to filter by (optional).
	 * @param  pageable              the page size, page number, and sorting of the result.
	 * @return                       a page of customers that meet all the criteria provided,
	 *                               or a page of all customers if no criteria were provided.
	 */
	@GetMapping("/filter")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public Page<Customer> filterCustomers(
			// These are the query parameters for filtering customers.
			// The "required = false" attribute indicates that the parameter is optional.
			@RequestParam(required = false) Double minAnnualIncome,
			@RequestParam(required = false) Double maxAnnualIncome,
			@RequestParam(required = false) LocalDate minRegistrationDate,
			@RequestParam(required = false) LocalDate maxRegistrationDate,
			@RequestParam(required = false) LocalDate minLastContactDate,
			@RequestParam(required = false) LocalDate maxLastContactDate,
			@RequestParam(required = false) String legalName,
			// This parameter specifies the page size, page number, and sorting of the
			// result.
			Pageable pageable) {

		// If no query parameters are provided, return a page of all customers.
		if (minAnnualIncome == null && maxAnnualIncome == null &&
				minRegistrationDate == null && maxRegistrationDate == null &&
				minLastContactDate == null && maxLastContactDate == null && legalName == null) {
			return service.getAllCustomersPage(pageable);
		}

		// This list will store the partial results for each query parameter that is
		// provided.
		List<Page<Customer>> partialResults = new ArrayList<>();

		// If both "minAnnualIncome" and "maxAnnualIncome" query parameters are
		// provided,
		// filter customers by annual income range.
		if (minAnnualIncome != null && maxAnnualIncome != null) {
			partialResults.add(service.getCustomersByAnnualIncomeRange(minAnnualIncome, maxAnnualIncome, pageable));
		}

		// If both "minRegistrationDate" and "maxRegistrationDate" query parameters are
		// provided,
		// filter customers by registration date range.
		if (minRegistrationDate != null && maxRegistrationDate != null) {
			partialResults.add(
					service.getCustomersByRegistrationDateRange(minRegistrationDate, maxRegistrationDate, pageable));
		}

		// If both "minLastContactDate" and "maxLastContactDate" query parameters are
		// provided,
		// filter customers by last contact date range.
		if (minLastContactDate != null && maxLastContactDate != null) {
			partialResults
					.add(service.getCustomersByLastContactDateRange(minLastContactDate, maxLastContactDate, pageable));
		}

		// If the "legalName" query parameter is provided, filter customers by legal name
		// containing the provided string.
		if (legalName != null) {
			partialResults.add(service.getCustomersByLegalNameContaining(legalName, pageable));
		}

		// This stream operation finds the intersection of all the partial results.
		List<Customer> commonCustomers = partialResults.stream()
				// Map each partial result to its content (a list of customers).
				.map(Page::getContent)
				// Flatten the list of lists into a single list.
				.flatMap(Collection::stream)
				// Group the customers in the list by identity and count the occurrences of each
				// customer.
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				// Keep only the entries whose count is equal to the number of partial results.
				.entrySet().stream()
				.filter(e -> e.getValue() == partialResults.size())
				// Map the entries back to the corresponding customers.
				.map(Map.Entry::getKey)
				// Collect the customers into a list.
				.collect(Collectors.toList());

		// This section constructs a page of the common customers based on the
		// "pageable" parameter.
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int startIndex = pageNumber * pageSize;
		int endIndex = Math.min(startIndex + pageSize, commonCustomers.size());
		List<Customer> pageContent = commonCustomers.subList(startIndex, endIndex);
		return new PageImpl<>(pageContent, pageable, commonCustomers.size());
	}

	// Exception handler for any other exceptions not handled by the above methods
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error("An error occurred: {}", e.getMessage());
		return new ResponseEntity<String>(
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
