package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
