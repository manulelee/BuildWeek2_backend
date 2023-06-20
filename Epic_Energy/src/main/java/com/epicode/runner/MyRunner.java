package com.epicode.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epicode.models.City;
import com.epicode.models.Province;
import com.epicode.service.CityService;
import com.epicode.service.ProvinceService;


@Component
public class MyRunner implements ApplicationRunner{

	@Autowired
	ProvinceService provinceService;
	
	@Autowired
	CityService cityService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		EpicEnergy e = new EpicEnergy();
//		
//		EpicEnergy e2 = new EpicEnergy();
		insertProvince();
		insertMunicipality();
		
		List<City> cities = cityService.getAllCities();
		System.out.println("Numero di città: " + cities.size());
		Object[] arrCities = cities.toArray();
		for (int i=0; i<arrCities.length; i++) {
			System.out.println("Città " + arrCities[i]);
		}
		
	}
	public void insertProvince() {
		
		 String file = "src/main/resources/province-italiane.csv";
	     String line;
	      try (
	    		  BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	  br.readLine();
	          while((line = br.readLine()) != null){
	              String[] fields = line.split(";");
	              System.out.println(fields[0] + " " +fields[1] + " " + fields[2]);
	            provinceService.createProvince(new Province(fields [0], fields [1], fields [2]));
	              
	          }
	      } catch (Exception e){
	          System.out.println(e);
	      }
	}
	
	public void insertMunicipality() {
		
		 String file = "src/main/resources/comuni-italiani.csv";
	     String line;
	      try (
	    		  BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	  br.readLine();
	          while((line = br.readLine()) != null){
	        	//  System.out.println(line);
	              String[] fields = line.split(";");
	             // System.out.println(fields[0]);
	             // System.out.println(fields[1]);
	            //  System.out.println(fields[2]);
	              
	              Province prov =provinceService.getProvinceByName(fields[3]);
	              
	              City city = new City(fields[2], prov);
	              cityService.createCity(city);
	     
	            //  System.out.println("provincia " + fields[3] + " ID comune " +fields[1] + " nome comune " + fields[2]);
	           // provinceService.createProvince(new Province(fields [0], fields [1], fields [2]));
	              
	          }
	      } catch (Exception e){
	          System.out.println(e);
	      }
	}
	
}
