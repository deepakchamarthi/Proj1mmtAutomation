package mmt.pageflow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mmt.drivermanager.DriverFactory;
import mmt.pages.BasePage;
import mmt.pages.HomePage;
import mmt.pages.RoomSelectionPage;

public class StartUpFlow {

	private static Logger logger = LogManager.getLogger(RoomSelectionPage.class);

	public static void main(String[] args) {

		DriverFactory.initiateDriver();
		HomePage hPage = new HomePage();
		// Open PageURL
		hPage.open();
		logger.info(" URL opened");

		// Runs Homepage Flow. Including City, Dates , Reason Selection

		HomePageFlow1 hpf = new HomePageFlow1();
		hpf.run();
		logger.info("Home Page Done");

		// Runs SearchPage Flow. Take cares adjusting price scroll bar, Filtering 4Star
		// hotel and Selecting 5th Hotel from the list (or close to 5th in case <5 hotels listed)
		SearchPageFlow spf = new SearchPageFlow();
		spf.run_SearchPage();
		logger.info("Search Page Done");

		//Runs RoomPageFlow to select the Room
		RoomPageFlow rpf = new RoomPageFlow();
		rpf.run_RoomSelectionPage();
		logger.info("Room Page Done");

	//ReivewPageflow to review the data and   
		TestReviewPageFlow tpf = new TestReviewPageFlow();
		tpf.run_ReviewPage();
		logger.info("Review Page Done");
		//to check contents in the payment page
		PaymentPageFlow ppf = new PaymentPageFlow();
		ppf.run_PaymentPage();
		logger.info("Payment Page Done");

	}

}
