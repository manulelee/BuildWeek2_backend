package com.epicode.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.epicode.models.Invoice;

public interface InvoicePageRepository extends PagingAndSortingRepository<Invoice, Long> {

}
