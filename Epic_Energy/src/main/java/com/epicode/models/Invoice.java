package com.epicode.models;

import java.time.LocalDate;

import com.epicode.Enumerations.InvoiceState;

import jakarta.persistence.Entity;
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
	
	private Long invoiceNumber;
//	private Integer year;
	private LocalDate date;
	private Double ammount;
	private Client client;
	private InvoiceState state;
	
	
}
