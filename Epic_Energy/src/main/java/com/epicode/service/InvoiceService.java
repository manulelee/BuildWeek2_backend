package com.epicode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.exceptions.InvoiceNotFoundException;
import com.epicode.models.Invoice;
import com.epicode.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository repository;

	public List<Invoice> getAllInvoices() {
		return repository.findAll();
	}

	public Invoice getInvoiceById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
	}

	public Invoice createInvoice(Invoice invoice) {
		System.out.print("invoice saved");
		return repository.save(invoice);
	}

	public Invoice updateInvoice(Long id, Invoice invoice) {
		return repository.findById(id)
				.map(existingInvoice -> {
					existingInvoice.setCustomer(invoice.getCustomer());
					existingInvoice.setDate(invoice.getDate());
					existingInvoice.setAmount(invoice.getAmount());
					return repository.save(existingInvoice);
				})
				.orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
	}

	public String removeInvoice(Long id) {
		if (!repository.existsById(id)) {
			throw new InvoiceNotFoundException("Invoice not found");
		}
		repository.deleteById(id);
		return "Invoice removed";
	}

}
