package com.epicode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.Invoice;
import com.epicode.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired private InvoiceRepository repository;
	
	public List<Invoice> getAllInvoices(){
		return repository.findAll();
	}
	
	public Invoice getInvoiceById (Long id) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		return repository.findById(id).get();
	}
	
	public Invoice createInvoice(Invoice invoice) {
		System.out.print("invoice saved");
		return repository.save(invoice);
	}
	
	public Invoice updateInvoice (Long id, Invoice invoice) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		return repository.save(invoice);
	}
	
	public String removeInvoice (Long id) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		repository.deleteById(id);
		return "Invoice removed";
	}
	
}