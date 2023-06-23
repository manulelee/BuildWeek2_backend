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

import com.epicode.exceptions.InvoiceNotFoundException;
import com.epicode.enumerations.InvoiceState;
import com.epicode.models.Customer;
import com.epicode.models.Invoice;
import com.epicode.service.CustomerService;
import com.epicode.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	InvoiceService service;

	@Autowired
	CustomerService customerService;

	@GetMapping("/all")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		return new ResponseEntity<List<Invoice>>(service.getAllInvoices(), HttpStatus.OK);
	}

	@GetMapping
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Invoice>> getAllInvoicesPage(Pageable pageable) {
		Page<Invoice> pageInvoice = service.getAllInvoicesPage(pageable);
		return ResponseEntity.ok(pageInvoice);
	}

	@GetMapping("/{id}")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
		try {
			Invoice invoice = service.getInvoiceById(id);
			return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
		} catch (InvoiceNotFoundException e) {
			return new ResponseEntity<String>("Invoice not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
		return new ResponseEntity<Invoice>(service.createInvoice(invoice), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateInvoice(
			@PathVariable Long id, @RequestBody Invoice invoice) {
		try {
			Invoice updatedInvoice = service.updateInvoice(id, invoice);
			return new ResponseEntity<Invoice>(updatedInvoice, HttpStatus.OK);
		} catch (InvoiceNotFoundException e) {
			return new ResponseEntity<String>("Invoice not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
		try {
			return new ResponseEntity<String>(
					service.removeInvoice(id), HttpStatus.OK);
		} catch (InvoiceNotFoundException e) {
			return new ResponseEntity<String>(
					"Invoice not found", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Filters invoices based on the given parameters and returns a page of invoices
	 * that match the criteria.
	 *
	 * @param customer  the ID of the customer to filter by, if provided
	 * @param state     the state of the invoices to filter by, if provided
	 * @param date      the date of the invoices to filter by, if provided
	 * @param year      the year of the invoices to filter by, if provided
	 * @param minAmount the minimum amount of the invoices to filter by, if provided
	 * @param maxAmount the maximum amount of the invoices to filter by, if provided
	 * @param pageable  the paging and sorting information
	 * @return a page of invoices that match the provided criteria
	 */
	@GetMapping("/filter")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public Page<Invoice> filterInvoices(
			// This annotation indicates that the parameter is a query parameter.
			// The "required = false" attribute indicates that the parameter is optional.
			@RequestParam(required = false) String customer,
			@RequestParam(required = false) InvoiceState state,
			@RequestParam(required = false) LocalDate date,
			@RequestParam(required = false) Integer year,
			@RequestParam(required = false) Double minAmount,
			@RequestParam(required = false) Double maxAmount,
			// This parameter specifies the page size, page number, and sorting of the
			// result.
			Pageable pageable) {

		// If no query parameters are provided, return a page of all invoices.
		if (customer == null && state == null && date == null &&
				year == null && minAmount == null && maxAmount == null) {
			return service.getAllInvoicesPage(pageable);
		}

		// This list will store the partial results for each query parameter that is
		// provided.
		List<Page<Invoice>> partialResults = new ArrayList<>();

		// If the "customer" query parameter is provided, filter invoices by customer.
		if (customer != null) {
			Customer customerObj = customerService.getCustomerById(customer);
			partialResults.add(service.getInvoicesByCustomer(customerObj, pageable));
		}

		// If the "state" query parameter is provided, filter invoices by state.
		if (state != null) {
			partialResults.add(service.getInvoicesByState(state, pageable));
		}

		// If the "date" query parameter is provided, filter invoices by date.
		if (date != null) {
			partialResults.add(service.getInvoicesByDate(date, pageable));
		}

		// If the "year" query parameter is provided, filter invoices by year.
		if (year != null) {
			partialResults.add(service.getInvoicesByYear(year, pageable));
		}

		// If both "minAmount" and "maxAmount" query parameters are provided,
		// filter invoices by amount range.
		if (minAmount != null && maxAmount != null) {
			partialResults.add(service.getInvoicesByAmountRange(minAmount, maxAmount, pageable));
		}

		// This stream operation finds the intersection of all the partial results.
		List<Invoice> commonInvoices = partialResults.stream()
				// Map each partial result to its content (a list of invoices).
				.map(Page::getContent)
				// Flatten the list of lists into a single list.
				.flatMap(Collection::stream)
				// Group the invoices in the list by identity and count the occurrences of each
				// invoice.
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				// Keep only the entries whose count is equal to the number of partial results.
				.entrySet().stream()
				.filter(e -> e.getValue() == partialResults.size())
				// Map the entries back to the corresponding invoices.
				.map(Map.Entry::getKey)
				// Collect the invoices into a list.
				.collect(Collectors.toList());

		// This section constructs a page of the common invoices based on the "pageable"
		// parameter.
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int startIndex = pageNumber * pageSize;
		int endIndex = Math.min(startIndex + pageSize, commonInvoices.size());
		List<Invoice> pageContent = commonInvoices.subList(startIndex, endIndex);
		Page<Invoice> page = new PageImpl<>(pageContent, pageable, commonInvoices.size());
		return page;
	}

	// Exception handler for any other exceptions not handled by the above methods
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error("An error occurred: {}", e.getMessage());
		return new ResponseEntity<String>(
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
