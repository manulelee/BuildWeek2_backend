package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    
}
