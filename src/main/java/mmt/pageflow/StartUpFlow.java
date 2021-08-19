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
		hPage.open();

		logger.info(" URL opened");

		HomePageFlow1 hpf = new HomePageFlow1();
		hpf.run();

		logger.info("Home Page Done");
		SearchPageFlow1 spf = new SearchPageFlow1();
		spf.run_SearchPage();
		logger.info("Search Page Done");

		RoomPageFlow rpf = new RoomPageFlow();
		rpf.run_RoomSelectionPage();
		logger.info("Room Page Done");

		TestReviewPageFlow tpf = new TestReviewPageFlow();
		tpf.run_ReviewPage();
		logger.info("Review Page Done");

		PaymentPageFlow ppf = new PaymentPageFlow();
		ppf.run_PaymentPage();
		logger.info("Payment Page Done");

	}

}
