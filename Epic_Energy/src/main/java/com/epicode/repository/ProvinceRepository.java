package com.epicode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.models.Province;

public interface ProvinceRepository extends JpaRepository<Province, String>{
	public Optional<Province> findByProvinceName(String name);
}
