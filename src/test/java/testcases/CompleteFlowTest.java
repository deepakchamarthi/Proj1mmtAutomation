package testcases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import mmt.drivermanager.DriverFactory;
import mmt.pageflow.HomePageFlow1;
import mmt.pageflow.PaymentPageFlow;
import mmt.pageflow.RoomPageFlow;
import mmt.pageflow.SearchPageFlow;
import mmt.pageflow.TestReviewPageFlow;

public class CompleteFlowTest {
	public static Logger logger = LogManager.getLogger(CompleteFlowTest.class);
	
	
	
	@BeforeMethod
	public void browserOpen() {
		DriverFactory.initiateDriver();
		logger.info("Driver opened on Thread ID::" + Thread.currentThread().getId());
	}
			
	
	@Test
	public void bookHotelFlow() {
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
		
		
		PaymentPageFlow ppf = new PaymentPageFlow();
		ppf.run_PaymentPage();
		logger.info("Payment Page Done");
		
	}
	
	
	
	
	
	@AfterMethod
	public void closeBrowser() {
		DriverFactory.getCurrentDriver().quit();
	}
	

}
