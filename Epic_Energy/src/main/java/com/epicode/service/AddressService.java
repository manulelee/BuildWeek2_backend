package com.epicode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.models.Address;
import com.epicode.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired private AddressRepository repository;
	
	public List<Address> getAllAddress(){
		return repository.findAll();
	}
	
	public Address getAddressById (Long id) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		return repository.findById(id).get();
	}
	
	public Address createAddress(Address address) {
		if (repository.findByStreetAndNumberAndLocalityAndZipCodeAndCity(address.getStreet(), address.getNumber(), address.getLocality(), address.getZipCode(), address.getCity())!= null) {
			//gestione eccezione
		}
		return repository.save(address);
	}
	
	public Address updateAddress (Long id, Address address) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		return repository.save(address);
	}
	
	public String removeAddress (Long id) {
		if (!repository.existsById(id)) {
			//gestione eccezione
		}
		repository.deleteById(id);
		return "Address removed";
	}
	
}
