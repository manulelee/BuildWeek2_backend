package com.epicode.models;

import java.time.LocalDate;


import com.epicode.enumerations.InvoiceState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
//	private Integer year;
	
	private LocalDate date;
	
	private Double amount;
	
	@ManyToOne
	private Customer customer;
	
	private InvoiceState state;
	
	
}
