package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Address;
import com.epicode.models.City;

public interface AddressRepository extends JpaRepository<Address, Long> {

	public Address findByStreetAndNumberAndLocalityAndZipCodeAndCity(
			String street, Integer number, String locality, Integer ZipCode, City city);
}
