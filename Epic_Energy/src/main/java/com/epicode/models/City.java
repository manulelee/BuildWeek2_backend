package com.epicode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "city_name", nullable = false)
	private String cityName;
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private Province province;
	
	
	public City(String name, Province province) {
		this.cityName=name;
		this.province= province;
	}
	
	@Override
	public String toString () {
		return "ID "+ this.id +" - " + this.cityName + " - (" + this.province.getAbbreviation() + ")";
	}
}
