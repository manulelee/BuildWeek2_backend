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
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		return new ResponseEntity<List<Invoice>>(service.getAllInvoices(), HttpStatus.OK);
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<Page<Invoice>> getAllInvoicesPage(Pageable pageable) {
		Page<Invoice> pageInvoice = service.getAllInvoicesPage(pageable);
		return ResponseEntity.ok(pageInvoice);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
		return new ResponseEntity<Invoice>(service.getInvoiceById(id), HttpStatus.OK);

	}

	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// System.out.println(authentication.getAuthorities());
		return new ResponseEntity<Invoice>(service.createInvoice(invoice), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Invoice updateCustomer(@PathVariable Long id, @RequestBody Invoice invoice) {
		return service.updateInvoice(id, invoice);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteInvoice(@PathVariable Long id) {
		return service.removeInvoice(id);
	}

	/**
	 * Filters invoices based on the given parameters and returns a page of invoices that match the criteria.
	 *
	 * @param  customer    the ID of the customer to filter by, if provided
	 * @param  state       the state of the invoices to filter by, if provided
	 * @param  date        the date of the invoices to filter by, if provided
	 * @param  year        the year of the invoices to filter by, if provided
	 * @param  minAmount   the minimum amount of the invoices to filter by, if provided
	 * @param  maxAmount   the maximum amount of the invoices to filter by, if provided
	 * @param  pageable    the paging and sorting information
	 * @return             a page of invoices that match the provided criteria
	 */
	@GetMapping("/filter")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public Page<Invoice> filterInvoices(
			@RequestParam(required = false) String customer,
			@RequestParam(required = false) InvoiceState state,
			@RequestParam(required = false) LocalDate date,
			@RequestParam(required = false) Integer year,
			@RequestParam(required = false) Double minAmount,
			@RequestParam(required = false) Double maxAmount,
			Pageable pageable) {

		if (customer == null && state == null && date == null &&
				year == null && minAmount == null && maxAmount == null) {
			return service.getAllInvoicesPage(pageable);
		}

		List<Page<Invoice>> partialResults = new ArrayList<>();

		if (customer != null) {
			Customer customerObj = customerService.getCustomerById(customer);
			partialResults.add(service.getInvoicesByCustomer(customerObj, pageable));
		}

		if (state != null) {
			partialResults.add(service.getInvoicesByState(state, pageable));
		}

		if (date != null) {
			partialResults.add(service.getInvoicesByDate(date, pageable));
		}

		if (year != null) {
			partialResults.add(service.getInvoicesByYear(year, pageable));
		}

		if (minAmount != null && maxAmount != null) {
			partialResults.add(service.getInvoicesByAmountRange(minAmount, maxAmount, pageable));
		}

		List<Invoice> commonInvoices = partialResults.stream()
				.map(Page::getContent)
				.flatMap(Collection::stream)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(e -> e.getValue() == partialResults.size())
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int startIndex = pageNumber * pageSize;
		int endIndex = Math.min(startIndex + pageSize, commonInvoices.size());
		List<Invoice> pageContent = commonInvoices.subList(startIndex, endIndex);
		return new PageImpl<>(pageContent, pageable, commonInvoices.size());
	}

}
