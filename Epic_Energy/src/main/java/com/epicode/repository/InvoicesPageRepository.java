package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Invoice;

public interface InvoicesPageRepository extends JpaRepository<Invoice, Long> {

}
