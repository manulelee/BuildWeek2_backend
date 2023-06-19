package com.epicode.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.epicode.models.EpicEnergy;

public class MyRunner implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		EpicEnergy e = new EpicEnergy();
		
		EpicEnergy e2 = new EpicEnergy();
	}
	
	
}
