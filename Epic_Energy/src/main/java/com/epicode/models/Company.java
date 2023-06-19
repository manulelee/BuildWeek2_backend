package com.epicode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Company {
	
	@Id
	@Column(name = "vat_number")
	protected String vatNumber;
	
	@Column(name= "legal_name", nullable = false)
	protected String legalName;
	
	@Column(nullable = false)
	protected String email;
	
	@Column(nullable = false)
	protected String pec;
	
	@Column(nullable = false)
	protected String phone;	
}
