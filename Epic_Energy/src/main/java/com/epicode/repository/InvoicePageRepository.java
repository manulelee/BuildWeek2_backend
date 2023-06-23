package com.epicode.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.epicode.enumerations.InvoiceState;
import com.epicode.models.Customer;
import com.epicode.models.Invoice;

public interface InvoicePageRepository extends PagingAndSortingRepository<Invoice, Long> {
    Page<Invoice> findByCustomer(Customer customer, Pageable pageable);

    Page<Invoice> findByState(InvoiceState state, Pageable pageable);

    Page<Invoice> findByDate(LocalDate date, Pageable pageable);

    Page<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    default Page<Invoice> findByYear(int year, Pageable pageable) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return findByDateBetween(startDate, endDate, pageable);
    }

    Page<Invoice> findByAmountBetween(Double minAmount, Double maxAmount, Pageable pageable);

}
