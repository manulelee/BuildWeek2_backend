package com.epicode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="provinces")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Province {
	
	@Id
	private String abbreviations;
	
	@Column(name = "province_name", nullable = false)
	private String provinceName;
	
	@Column(nullable = false)
	private String region;
	
}
