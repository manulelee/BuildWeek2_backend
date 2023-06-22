package com.epicode.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.epicode.models.Invoice;

public interface InvoicesPageRepository extends PagingAndSortingRepository<Invoice, Long> {

}
