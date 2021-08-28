package testcases;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import mmt.bo.HomePageBO;
import mmt.drivermanager.DriverFactory;
import mmt.pageflow.HomePageFlow1;
import mmt.pageflow.RoomPageFlow;
import mmt.pageflow.SearchPageFlow;
import mmt.pageflow.TestReviewPageFlow;
import mmt.pages.HomePage;
import mmt.pages.PaymentPage;

public class AssertBookingSummary {

	//Aim of this test is to check the "CheckinDates" at the end of the booking scenario 
	//expectation is that, it should match with the data provided by BO class.
	//Helps to confirm the same data is flowed through the entire process. 
	public static Logger logger = LogManager.getLogger(CompleteFlowTest.class);
	
	@BeforeMethod
	public void browserOpen() {
		DriverFactory.initiateDriver();
		logger.info("Driver opened on Thread ID::" + Thread.currentThread().getId());
	}

	@Test
	public void checkBookingSummary() {

		
		
		PaymentPage pay = new PaymentPage();
		
			HomePageFlow1 hpf = new HomePageFlow1();
			hpf.run();
			logger.info("Home Page Test Completed");
			
			SearchPageFlow spf = new SearchPageFlow();
			spf.run_SearchPage();
			logger.info("Search Page Done");
						
			
			RoomPageFlow rpf = new RoomPageFlow();
			rpf.run_RoomSelectionPage();
			logger.info("Room Page Done");
			
			TestReviewPageFlow tpf = new TestReviewPageFlow();
			tpf.run_ReviewPage();
			logger.info("Review Page Done");
			
			
		HomePage hp= new HomePage();
		hp.setHomePageBO();
		HomePageBO home = new HomePageBO();
		String checkinDate = home.getCheckInDate();
		System.out.println(checkinDate);
		List<String> checkInOutDates = pay.getDetails();
		System.out.println(checkInOutDates);
		System.out.println(checkInOutDates.get(0).substring(5,7));
		
//Sample Assertion to check if input and output dates are matching or not
		if (checkInOutDates.get(0).contains(checkinDate))
			Assert.assertTrue(true, "Checkin Date Matched");
	}

	@AfterMethod
	public void closeBrowser() {
		DriverFactory.getCurrentDriver().quit();
	}
}
