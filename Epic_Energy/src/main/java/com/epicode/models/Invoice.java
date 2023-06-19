package com.epicode.models;

import java.time.LocalDate;


import com.epicode.enumerations.InvoiceState;

import jakarta.persistence.Entity;
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
	private Long invoiceNumber;
	
//	private Integer year;
	
	private LocalDate date;
	
	private Double ammount;
	
	@ManyToOne
	private Customer customer;
	
	private InvoiceState state;
	
	
}
