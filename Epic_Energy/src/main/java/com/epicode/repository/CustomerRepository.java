package com.epicode.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
}
