package testcases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import mmt.drivermanager.DriverFactory;
import mmt.pageflow.HomePageFlow;
import mmt.pageflow.HomePageFlow1;
import mmt.pageflow.PaymentPageFlow;
import mmt.pageflow.RoomPageFlow;
import mmt.pageflow.SearchPageFlow1;
import mmt.pageflow.TestReviewPageFlow;

public class CompleteFlowTest {
	public static Logger logger = LogManager.getLogger(CompleteFlowTest.class);
	
	
	
	@BeforeMethod
	public void browserOpen() {
		DriverFactory.initiateDriver();
		logger.info("Driver opened on Thread ID::" + Thread.currentThread().getId());
	}
			
	
	@Test
	public void homePageFlow() {
		HomePageFlow1 hpf = new HomePageFlow1();
		hpf.run();
		logger.info("Home Page Test Completed");
		
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
	/*
	@Test(dependsOnMethods = {"homePageFlow"})
	public void SearchPageFlow() throws InterruptedException {
		SearchPageFlow1 spf = new SearchPageFlow1();
		spf.run_SearchPage();
		logger.info("Search Page Test Completed");
	}
	
	
	@Test(dependsOnMethods = {"SearchPageFlow"})
	public void RoomPageFlow() throws InterruptedException {
		RoomPageFlow rpf = new RoomPageFlow();
		rpf.run_RoomSelectionPage();
		logger.info("Room Page Test Completed");
	}
	
	
	@Test(dependsOnMethods = {"RoomPageFlow"})
	public void ReviewPageFlow() throws InterruptedException {
		TestReviewPageFlow tpf = new TestReviewPageFlow();
		tpf.run_ReviewPage();
		logger.info("Review Page Test Completed");
	}
	
	@Test(dependsOnMethods = {"ReviewPageFlow"}, alwaysRun = true,priority = 5)
	public void PaymentPageFlow() throws InterruptedException {
		PaymentPageFlow ppf = new PaymentPageFlow();
		ppf.run_PaymentPage();
		logger.info("Payment Page Test Completed");
	}
	
	
	*/
	
	
	
	
	@AfterMethod
	public void closeBrowser() {
		DriverFactory.getCurrentDriver().quit();
	}
	

}
