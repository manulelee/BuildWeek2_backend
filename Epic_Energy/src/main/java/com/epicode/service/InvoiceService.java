package com.epicode.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.epicode.enumerations.InvoiceState;
import com.epicode.models.Customer;
import com.epicode.models.Invoice;
import com.epicode.repository.InvoiceRepository;
import com.epicode.repository.InvoicePageRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository repository;
	@Autowired
	private InvoicePageRepository repoPage;

	public List<Invoice> getAllInvoices() {
		return repository.findAll();
	}

	public Page<Invoice> getAllInvoicesPage(Pageable pageable) {
		return repoPage.findAll(pageable);
	}

	public Invoice getInvoiceById(Long id) {
		if (!repository.existsById(id)) {
			// gestione eccezione
		}
		return repository.findById(id).get();
	}

	public Invoice createInvoice(Invoice invoice) {
		System.out.print("invoice saved");
		return repository.save(invoice);
	}

	public Invoice updateInvoice(Long id, Invoice invoice) {
		if (!repository.existsById(id)) {
			// gestione eccezione
		}
		return repository.save(invoice);
	}

	public String removeInvoice(Long id) {
		if (!repository.existsById(id)) {
			// gestione eccezione
		}
		repository.deleteById(id);
		return "Invoice removed";
	}

	// Queries
	public Page<Invoice> getInvoicesByCustomer(Customer customer, Pageable pageable) {
		return repoPage.findByCustomer(customer, pageable);
	}

	public Page<Invoice> getInvoicesByState(InvoiceState state, Pageable pageable) {
		return repoPage.findByState(state, pageable);
	}

	public Page<Invoice> getInvoicesByDate(LocalDate date, Pageable pageable) {
		return repoPage.findByDate(date, pageable);
	}

	public Page<Invoice> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
		return repoPage.findByDateBetween(startDate, endDate, pageable);
	}

	public Page<Invoice> getInvoicesByYear(int year, Pageable pageable) {
		return repoPage.findByYear(year, pageable);
	}

	public Page<Invoice> getInvoicesByAmountRange(Double minAmount, Double maxAmount, Pageable pageable) {
		return repoPage.findByAmountBetween(minAmount, maxAmount, pageable);
	}

}