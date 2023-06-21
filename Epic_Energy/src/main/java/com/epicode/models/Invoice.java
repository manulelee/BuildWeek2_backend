package com.epicode.models;

import java.time.LocalDate;


import com.epicode.enumerations.InvoiceState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="invoices")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "invoice_number")
	private Long invoiceNumber;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(nullable = false)
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name = "customer_vat_number", referencedColumnName = "vat_number", nullable = false)
	private Customer customer;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private InvoiceState state;
	
}
