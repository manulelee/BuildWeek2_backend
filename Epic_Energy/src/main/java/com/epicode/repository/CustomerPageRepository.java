package com.epicode.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.epicode.models.Customer;

public interface CustomerPageRepository extends PagingAndSortingRepository<Customer, String> {

}
