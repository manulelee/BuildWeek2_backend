package com.epicode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private Integer number;

	private String locality;

	@Column(name = "zip_code", nullable = false)
	private Integer zipCode;

	@JoinColumn(nullable = false)
	@ManyToOne
	private City city;

	public Address(
			String street, Integer number, String locality,
			Integer zipCode, City city) {
		this.street = street;
		this.number = number;
		this.locality = locality;
		this.zipCode = zipCode;
		this.city = city;
	}
}
