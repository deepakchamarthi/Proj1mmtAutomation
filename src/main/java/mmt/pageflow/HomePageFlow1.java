package mmt.pageflow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import mmt.drivermanager.DriverFactory;
import mmt.pages.HomePage;

public class HomePageFlow1 {

	public static void main(String[] args)   {
		// TODO Auto-generated method stub
		DriverFactory.initiateDriver();
		HomePage hPage = new HomePage();
		hPage.open();
		//run();

	}

	public   void run()  {

		HomePage hPage = new HomePage();
		hPage.open();

		hPage.selectCity(hPage.city_Text, "Delhi");

		hPage.clickOnChkInChekOut(hPage.checkIn_button);

		hPage.clickOnguests(hPage.guest_ButtonId);
		hPage.SelectGuestCount(4, 3);

		hPage.selectApply(hPage.apply_Button);
		hPage.travelReson();
		hPage.submit();

	}

}
