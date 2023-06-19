package com.epicode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {

	@Id
	private Long id;
	
	@Column(name = "city_name", nullable = false)
	private String cityName;
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private Province province;
	
}
