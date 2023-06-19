package com.epicode.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epicode.Enumerations.ClientType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="clients")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Client extends Company {

	@Column(name="registration", nullable = false)
	private LocalDate registrationDate;
	
	@Column(name = "last_contact")
	private LocalDate lastContactDate;
	
	@Column(name = "annual_income", nullable = false)
	private Double annualIncome;
	
	@Column(name = "contact_email", nullable = false)
	private String contactEmail;
	
	@Column(name = "contact_name", nullable = false)
	private String contactName;
	
	@Column(name = "contact_last_name", nullable = false)
	private String contactLastName;
	
	@Column(name = "contact_phone", nullable = false)
	private String contactPhone;
	
	@Column(nullable = false)
	private ClientType category;
	
	@Column(nullable = false)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Invoice> invoices = new ArrayList<Invoice>();
	
	
}
