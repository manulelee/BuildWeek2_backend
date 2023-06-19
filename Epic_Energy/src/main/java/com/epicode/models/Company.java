package com.epicode.models;

import java.util.HashMap;
import java.util.Map;

import com.epicode.Enumerations.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class Company {
	
	@Column(unique = true)
	private String vatNumber;
	
	@Column(nullable = false)
	private String legalName;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String pec;
	
	@Column(nullable = false)
	private String phone;
	
//	@JoinColumn(nullable = false)
	@ManyToMany(fetch = FetchType.EAGER)
	private Map<AddressType, Address> address = new HashMap<AddressType, Address>();
	
	
}
