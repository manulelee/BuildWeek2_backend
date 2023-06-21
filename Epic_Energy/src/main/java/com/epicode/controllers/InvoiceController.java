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

import com.epicode.exceptions.InvoiceNotFoundException;
import com.epicode.models.Invoice;
import com.epicode.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	InvoiceService service;
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Invoice>> getAllInvoices (){
		return new ResponseEntity<List<Invoice>>(service.getAllInvoices(),HttpStatus.OK); 	
	}
	
	@GetMapping
	@ResponseBody
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
		Invoice createdInvoice = service.createInvoice(invoice);
		return new ResponseEntity<Invoice>(createdInvoice, HttpStatus.CREATED);
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
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
		try {
			service.removeInvoice(id);
			return new ResponseEntity<String>(
					"Invoice deleted successfully", HttpStatus.OK);
		} catch (InvoiceNotFoundException e) {
			return new ResponseEntity<String>(
					"Invoice not found", HttpStatus.NOT_FOUND);
		}
	}

	// Exception handler for any other exceptions not handled by the above methods
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error("An error occurred: {}", e.getMessage());
		return new ResponseEntity<String>(
				"An error occurred :()", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
