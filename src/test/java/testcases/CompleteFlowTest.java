package testcases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import mmt.drivermanager.DriverFactory;
import mmt.pageflow.HomePageFlow1;
import mmt.pageflow.PaymentPageFlow;
import mmt.pageflow.RoomPageFlow;
import mmt.pageflow.SearchPageFlow;
import mmt.pageflow.TestReviewPageFlow;


//This is complete flow test & Generates sample  Test Report under  output folder with name TestReport_timestamp.html
public class CompleteFlowTest {
	
	
	static Logger logger = LogManager.getLogger(CompleteFlowTest.class);
	static ExtentTest test;
	static ExtentReports report;
	
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	@BeforeClass
	public static void startTest()
	{
	//configuring for Extent Reports	
	report = new ExtentReports("output\\TestReport_"+timeStamp+".html",true);
	test = report.startTest("CompleteFlowTest");
	}
	
		
	
	@Test
	public void bookHotelFlow() {
		//OPen the browser
		browserOpen();
		
		//checks all the pages
		HomePageFlow1 hpf = new HomePageFlow1();
		hpf.run();
		logger.info("Home Page Test Completed");
		test.log(LogStatus.PASS, "Home Page Test Completed");
		
		SearchPageFlow spf = new SearchPageFlow();
		spf.run_SearchPage();
		logger.info("Search Page Done");
		test.log(LogStatus.PASS, "Search Page Done");
					
		
		RoomPageFlow rpf = new RoomPageFlow();
		rpf.run_RoomSelectionPage();
		logger.info("Room Page Done");
		test.log(LogStatus.PASS, "Room Page Done");
		
		TestReviewPageFlow tpf = new TestReviewPageFlow();
		tpf.run_ReviewPage();
		logger.info("Review Page Done");
		test.log(LogStatus.PASS, "Review Page Done");
		
		
		PaymentPageFlow ppf = new PaymentPageFlow();
		ppf.run_PaymentPage();
		logger.info("Payment Page Done");
		test.log(LogStatus.PASS, "Payment Page Done");
		
	}
	
	//Open browser
	public void browserOpen() {
		DriverFactory.initiateDriver();
		logger.info("Driver opened on Thread ID::" + Thread.currentThread().getId());
		
		
	}
	
	//Close browser and Generate Report at folder: output
	@AfterClass
	public void closeBrowser() {
		DriverFactory.getCurrentDriver().quit();
		report.endTest(test);
		report.flush();
	}
	

}
