package com.epicode.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epicode.enumerations.AddressType;
import com.epicode.enumerations.ClientType;
import com.epicode.models.Address;
import com.epicode.models.City;
import com.epicode.models.Customer;
import com.epicode.models.Province;
import com.epicode.service.AddressService;
import com.epicode.service.CityService;
import com.epicode.service.CustomerService;
import com.epicode.service.ProvinceService;
import com.github.javafaker.Faker;

@Component
public class MyRunner implements ApplicationRunner {

	@Autowired
	ProvinceService provinceService;

	@Autowired
	CityService cityService;

	@Autowired
	AddressService addressService;
	
	@Autowired
	CustomerService customerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		insertProvince();
		insertMunicipality();
		//insertAddress();
		insertCustomer();
	}

	public void insertProvince() {
		if (!provinceService.getAllProvinces().isEmpty()) {
			return;
		}
		//String file = "Epic_Energy/src/main/resources/province-italiane.csv";
		String file = "src/main/resources/province-italiane.csv";
		String line;
		try (
				BufferedReader br = new BufferedReader(new FileReader(file))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(";");
				System.out.println(fields[0] + " " + fields[1] + " " + fields[2]);
				provinceService.createProvince(new Province(fields[0], fields[1], fields[2]));

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertMunicipality() {
		if (!cityService.getAllCities().isEmpty()) {
			return;
		}
		//String file = "Epic_Energy/src/main/resources/comuni-italiani.csv";
		String file = "src/main/resources/comuni-italiani.csv";
		String line;
		try (
				BufferedReader br = new BufferedReader(new FileReader(file))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(";");
				Province prov = provinceService.getProvinceByName(fields[3]);
				City city = new City(fields[2], prov);
				cityService.createCity(city);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Address insertAddress() {
		Faker faker = new Faker(new Locale("it", "IT"));
		List<City> cities = this.cityService.getAllCities();
	//	for (int i = 0; i < 10; i++) {
			String street = faker.address().streetName();
			System.out.println(street);
			Integer number = Integer.parseInt(faker.address().buildingNumber());
			System.out.println(number);
			String locality = faker.lorem().words(1).get(0);
			Integer zipCode = Integer.parseInt(
					faker.address().zipCode());
			System.out.println(zipCode);
			City city = cities.get(
					faker.number().numberBetween(0, cities.size() - 1));
			System.out.println(city);
			Address address = new Address(
					street, number, locality, zipCode, city);
			System.out.println(address);
			return address;
	//		addressService.createAddress(address);
	//	}
	}
	
	public String randomVatNumber() {
		Faker faker = new Faker(new Locale("it", "IT"));
		String vat = "";
		for (int i = 0; i < 11; i++) {
			vat += faker.number().numberBetween(0, 9);;
		}
		return vat;
	}
	
	public void insertCustomer () {
		Faker faker = new Faker(new Locale("it", "IT"));
		for (int i = 0; i < 10; i++) {
			String vat = randomVatNumber();
			String legalName = faker.company().name(); //da modificare
			String email = "amministrazione"+ "@" +legalName.split(" ")[0].toLowerCase() + ".com";
			String pec = legalName.split(" ")[0].toLowerCase() + "@pec.com";
			String phone = faker.phoneNumber().phoneNumber();
			String contactName = faker.name().firstName();
			String contactLastName = faker.name().lastName();
			String contactEmail = contactName.toLowerCase() + "." + contactLastName.toLowerCase() + "@" + legalName.split(" ")[0].toLowerCase() + ".com";
			String contactPhone = faker.phoneNumber().cellPhone();
			
			Customer customer = new Customer();
			customer.setVatNumber(vat);
			customer.setLegalName(legalName);
			customer.setEmail(email);
			customer.setPec(pec);
			customer.setPhone(phone);
			customer.setRegistrationDate(LocalDate.now());
			customer.setAnnualIncome(0.0);
			customer.setContactName(contactName);
			customer.setContactLastName(contactLastName);
			customer.setContactPhone(contactPhone);
			customer.setContactEmail(contactEmail);
			customer.setCategory(ClientType.SRL);
			customer.getAddress().put(AddressType.OPERATIVE_SEAT, insertAddress());
			
			System.out.println(customer);
			customerService.createCustomer(customer);
		
		}
	}

}
