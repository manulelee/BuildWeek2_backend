package com.epicode.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epicode.enumerations.AddressType;
import com.epicode.enumerations.ClientType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

	@Id
	@Column(name = "vat_number")
	protected String vatNumber;

	@Column(name = "legal_name", nullable = false)
	protected String legalName;

	@Column(nullable = false)
	protected String email;

	@Column(nullable = false)
	protected String pec;

	@Column(nullable = false)
	protected String phone;

	@Column(name = "registration", nullable = false)
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
	@Enumerated(EnumType.STRING)
	private ClientType category;

	@Column(nullable = false)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Invoice> invoices = new ArrayList<Invoice>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@MapKeyEnumerated(EnumType.STRING)
	protected Map<AddressType, Address> address = new HashMap<AddressType, Address>();

	@Override
	public String toString() {
		return "P.IVA: " + vatNumber + " - " + legalName + " " + email + " " + pec + " " + phone
				+ " informazioni contatto: " + contactEmail + " " + " " + contactPhone + " - numero fatture: "
				+ invoices.size() + address;
	}
}
