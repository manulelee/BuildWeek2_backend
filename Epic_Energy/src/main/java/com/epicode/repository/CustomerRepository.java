package com.epicode.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	Page<Customer> findByAnnualIncomeBetween(Double minIncome, Double maxIncome, Pageable pageable);

	Page<Customer> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	Page<Customer> findByLastContactDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	Page<Customer> findByLegalNameContaining(String name, Pageable pageable);
}
