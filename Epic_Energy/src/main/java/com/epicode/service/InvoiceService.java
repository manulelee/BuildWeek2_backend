package com.epicode.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.epicode.enumerations.InvoiceState;
import com.epicode.exceptions.InvoiceNotFoundException;
import com.epicode.models.Customer;
import com.epicode.models.Invoice;
import com.epicode.repository.InvoiceRepository;
import com.epicode.repository.InvoicesPageRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository repository;

	@Autowired
	private InvoicesPageRepository repoPage;

	public List<Invoice> getAllInvoices() {
		return repository.findAll();
	}

	public Page<Invoice> getAllInvoicesPage(Pageable pageable) {
		return repoPage.findAll(pageable);
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
