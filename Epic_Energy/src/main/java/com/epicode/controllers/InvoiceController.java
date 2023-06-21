package com.epicode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.models.Invoice;
import com.epicode.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	InvoiceService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		return new ResponseEntity<List<Invoice>>(service.getAllInvoices(), HttpStatus.OK);

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
//		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication.getAuthorities());
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

}
