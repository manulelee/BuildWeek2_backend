package com.epicode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cities")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class City {

	@Id
	private Long id;
	
	@Column(name = "city_name", nullable = false)
	private String cityName;
	
	@Column(nullable = false)
	private Province province;
	
}
