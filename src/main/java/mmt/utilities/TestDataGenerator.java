package mmt.utilities;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import mmt.bo.FillGuestDetailsBO;

public class TestDataGenerator {

	//creatng testdata to fill the GuestDetails 
	//Using Faker to generate random data useful to fill at guest details page
	public static List<FillGuestDetailsBO> generateGuestData(FillGuestDetailsBO guests) {

		List<FillGuestDetailsBO> dataList = new ArrayList<FillGuestDetailsBO>();

		Faker faker = new Faker();

	

		guests.setFirstName(faker.name().firstName());
		guests.setLastName(faker.name().lastName());
		guests.setEmail(faker.internet().emailAddress());
		guests.setMobile(faker.number().numberBetween(0, 9) + faker.number().digits(9));

		dataList.add(guests);

		
		return dataList;
	}

}
