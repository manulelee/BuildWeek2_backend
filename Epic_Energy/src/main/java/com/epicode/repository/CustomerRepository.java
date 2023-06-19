package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

}
