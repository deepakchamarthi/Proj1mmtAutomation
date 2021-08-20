package mmt.pageflow;

import mmt.pages.HomePage;

//The class contain flow to test MakeMyTrip/hotels home page.
//That includes selecting city, Guests count , travelreason and Submit the filters.

public class HomePageFlow1 {

	// Commenting the main method.
	// Uncomment to test only "HomePageFlow"
	
	 /* public static void main(String[] args) {
	  
	  
	  DriverFactory.initiateDriver(); HomePage hPage = new HomePage();
	  hPage.open(); 
	  run();
	  
	  } */
	 

	public  void run() {

		// Creating an object to HomePage Class -> where all the element interaction
	
		HomePage hPage = new HomePage();
		// Opening the URL
		hPage.open();
		
		hPage.setHomePageBO();
		//Selecting city element and entering the city name
		hPage.selectCity(hPage.city_Text);
		//Clicking on Checkin and Checkout button
		hPage.clickOnChkInChekOut(hPage.checkIn_button);
		//Click on the guests button 
		hPage.clickOnguests(hPage.guest_ButtonId);

		//Select the adults count: 4 and Children:3
		hPage.SelectGuestCount();
		
		
		
		hPage.selectChildAge();
		//Click on Apply button
		hPage.selectApply(hPage.apply_Button);
		//click on travelReason. 
		hPage.travelReson();
		//Submit the selection
		hPage.submit();

	}

}
