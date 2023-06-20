package com.epicode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Invoice>> getAllInvoices (){
		return new ResponseEntity<List<Invoice>>(service.getAllInvoices(),HttpStatus.OK); 
			
	}
	@GetMapping("/id")
	@ResponseBody
	public ResponseEntity<Invoice> getInvoiceById (Long id){
	return new ResponseEntity<Invoice>(service.getInvoiceById(id),HttpStatus.OK); 
			
	}
	
	@PostMapping
	@ResponseBody
	public Invoice createInvoice(@RequestBody Invoice invoice) {
		return service.createInvoice(invoice);
	}
	
	@PutMapping("/id")
	@ResponseBody
	public Invoice updateCustomer(@RequestBody Invoice invoice) {
		return service.updateInvoice(invoice.getInvoiceNumber(), invoice);
	}
	
	@DeleteMapping("/id")
	@ResponseBody
	public String deleteInvoice(Long id) {
		return service.removeInvoice(id);
	}
	
}
