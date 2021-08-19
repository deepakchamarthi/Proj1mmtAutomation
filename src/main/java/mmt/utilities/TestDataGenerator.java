package mmt.utilities;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;


import mmt.bo.FillGuestDetailsBO;

public class TestDataGenerator {
	
	
	
	
	
	
	public static List<FillGuestDetailsBO> generateGuestData(FillGuestDetailsBO guests)
	{
		
		List<FillGuestDetailsBO> dataList = new ArrayList<FillGuestDetailsBO>();
		
		Faker faker = new Faker();
		
		//FillGuestDetailsBO guests = new FillGuestDetailsBO();
		
		
		
				
		guests.setFirstName(faker.name().firstName());
		guests.setLastName(faker.name().lastName());
		guests.setEmail(faker.internet().emailAddress());
		guests.setMobile(faker.number().numberBetween(0,9)+ faker.number().digits(9));
		
		
	
				
		dataList.add(guests);
		
		//System.out.println(dataList);
		//System.out.println(guests);
		return dataList;
	}
	
	
	
	

}
