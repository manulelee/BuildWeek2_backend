package com.epicode.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epicode.enumerations.AddressType;
import com.epicode.enumerations.ClientType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Customer extends Company {

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
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	
	private List<Invoice> invoices = new ArrayList<Invoice>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	protected Map<AddressType, Address> address = new HashMap<AddressType, Address>();
	
	
}