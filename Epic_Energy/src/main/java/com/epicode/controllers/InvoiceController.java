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
@RequestMapping("/invoices")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired InvoiceService service;

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
	public ResponseEntity<Invoice> getInvoiceById (@PathVariable Long id){
	return new ResponseEntity<Invoice>(service.getInvoiceById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Invoice createInvoice(@RequestBody Invoice invoice) {
		return service.createInvoice(invoice);
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
