package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
