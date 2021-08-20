package proj1mmt.dataprovider;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;


public class DataProvider {
	
	
	@DataProvider
	public Object[][] getUserData() {
		Faker faker = new Faker();
		int dataSize = 3;

		Object[][] object = new Object[dataSize][1];

		for (int i = 0; i < 3; i++) {
			UserBO user = new UserBO();
			user.setId(faker.internet().emailAddress());
			user.setPassword(faker.name().username());
			user.setFirstName(faker.name().firstName());
			object[i][0] = user;
			// 00 -> user1
			// 10 -> user2
			// 20 -> user3
		}

		return object;
	}

}
