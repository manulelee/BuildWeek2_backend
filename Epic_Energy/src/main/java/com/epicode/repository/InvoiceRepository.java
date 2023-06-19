package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
