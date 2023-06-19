package com.epicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.models.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String>{

}
