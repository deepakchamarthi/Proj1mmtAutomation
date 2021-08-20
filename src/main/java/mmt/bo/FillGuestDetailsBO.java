package mmt.bo;

import com.github.javafaker.Faker;

import mmt.utilities.TestDataGenerator;

public class FillGuestDetailsBO {

	
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public  String toString() {
		
		//System.out.println(firstName+lastName+email+mobile);
		return "SearchFiltersBO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobile="
				+ mobile + "]";
		
	}
	
	/**
	 * 
	 * @param guests = Guest Details from FillGuestDetailsBO Class
	 */
	//TestDataGenerator method to fill guests details.
	public void filldetails(FillGuestDetailsBO guests)
	{
		TestDataGenerator.generateGuestData(guests);
		
	}
	
	
	//For Testing the data. Commented. 
	/*public static void main(String args[])
	{
		
		Faker faker = new Faker();
		FillGuestDetailsBO guests = new FillGuestDetailsBO();
		TestDataGenerator.generateGuestData(guests);
		
		System.out.println(guests.toString());
	} */
	
	
	
	
	
}
