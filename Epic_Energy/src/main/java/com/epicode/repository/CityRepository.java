package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
