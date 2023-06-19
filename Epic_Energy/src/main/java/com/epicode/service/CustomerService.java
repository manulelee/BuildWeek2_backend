package com.epicode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.Customer;
import com.epicode.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired private CustomerRepository repository;
	
	public List<Customer> getAllCustomers(){
		return repository.findAll();
	}
	
	public Customer getCustomerById (String vatNumber) {
		if (!repository.existsById(vatNumber)) {
			//gestione eccezione
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
			//gestione eccezione
		}
		return repository.save(customer);
	}
	
	public String removeCustomer (String vatNumber) {
		if (!repository.existsById(vatNumber)) {
			//gestione eccezione
		}
		repository.deleteById(vatNumber);
		return "Address removed";
	}
	
}