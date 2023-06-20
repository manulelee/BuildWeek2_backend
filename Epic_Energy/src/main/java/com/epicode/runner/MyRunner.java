package com.epicode.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epicode.models.Address;
import com.epicode.models.City;
import com.epicode.models.Province;
import com.epicode.service.AddressService;
import com.epicode.service.CityService;
import com.epicode.service.ProvinceService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;
import com.github.javafaker.Faker;

@Component
public class MyRunner implements ApplicationRunner {

	@Autowired
	ProvinceService provinceService;

	@Autowired
	CityService cityService;

	@Autowired
	AddressService addressService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		insertProvince();
		insertMunicipality();
		insertAddress();
	}

	public void insertProvince() {
		if (!provinceService.getAllProvinces().isEmpty()) {
			return;
		}
		String file = "Epic_Energy/src/main/resources/province-italiane.csv";
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
		String file = "Epic_Energy/src/main/resources/comuni-italiani.csv";
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

	public void insertAddress() {
		Faker faker = new Faker(new Locale("it", "IT"));
		List<City> cities = this.cityService.getAllCities();
		for (int i = 0; i < 10; i++) {
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
			addressService.createAddress(address);
		}
	}

}
