package com.epicode.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.epicode.enumerations.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "epic_energy")
@Getter
@Setter
public class EpicEnergy extends Company{
	
	@Bean
	@Scope("singleton")
	public EpicEnergy epicEnergy() {
		EpicEnergy e = new EpicEnergy();
		e.vatNumber= "IT000122345";
		e.legalName = "Epic Energy";
		e.email = "epic@energy.com";
		e.phone= "+39 3939928467";
		e.pec= "epic.energy@pec.it";
		return e;
	}
	
}
