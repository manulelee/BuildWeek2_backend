package com.epicode.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.epicode.exceptions.CustomerNotFoundException;
import com.epicode.models.Customer;
import com.epicode.repository.CustomerPageRepository;
import com.epicode.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired private CustomerRepository repository;
	@Autowired private CustomerPageRepository repoPage;
	
	public List<Customer> getAllCustomers(){
		return repository.findAll();
	}
	
	public Page<Customer> getAllCustomersPage(Pageable pageable){
		return repoPage.findAll(pageable);
	}
	
	public Customer getCustomerById (String vatNumber) {
		if (!repository.existsById(vatNumber)) {
			throw new CustomerNotFoundException(vatNumber);
		}
		return repository.findById(vatNumber).get();
	}
	
	public Customer createCustomer(Customer customer) {
		if (repository.findById(customer.getVatNumber())!= null) {
			//gestione eccezione
		}
		return repository.save(customer);
	}
	
	public Customer updateCustomer (String vatNumber, Customer customer) {
		if (!repository.existsById(vatNumber)) {
			throw new CustomerNotFoundException(vatNumber);
		}
		return repository.save(customer);
	}
	
	public String removeCustomer (String vatNumber) {
		if (!repository.existsById(vatNumber)) {
			throw new CustomerNotFoundException("Invoice not found");
		}
		repository.deleteById(vatNumber);
		return "Customer removed";
	}
	
	//Queries
	
	public Page<Customer> getCustomersByAnnualIncomeRange(Double minIncome, Double maxIncome, Pageable pageable) {
        return repoPage.findByAnnualIncomeBetween(minIncome, maxIncome, pageable);
    }

    public Page<Customer> getCustomersByRegistrationDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return repoPage.findByRegistrationDateBetween(startDate, endDate, pageable);
    }

    public Page<Customer> getCustomersByLastContactDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return repoPage.findByLastContactDateBetween(startDate, endDate, pageable);
    }

    public Page<Customer> getCustomersByLegalNameContaining(String name, Pageable pageable) {
        return repoPage.findByLegalNameContaining(name, pageable);
    }


	
}