package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.City;
import com.epicode.models.Province;

public interface CityRepository extends JpaRepository<City, Long> {
	public City findByCityNameAndProvince(
			String cityName, Province province);

}
