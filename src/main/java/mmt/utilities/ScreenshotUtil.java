package mmt.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import mmt.drivermanager.DriverFactory;
import mmt.pages.BasePage;
import mmt.pages.SearchPage;



public class ScreenshotUtil {
	static Logger logger = LogManager.getLogger(SearchPage.class);
	
	public static int counter=1; 
	private static String outputFolder = "C:\\Users\\Priyanka\\eclipse-workspace\\proj1mmt\\src\\main\\Output";
	
	public static void main(String args[])
	{
		WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();
				driver.get("https://www.google.com/?gws_rd=ssl");
		
		
		for( int i =0;i<3;i++)
		{
		 getScreenshot(driver,"google");
		 getScreenshot(driver);
		}
	}
	
	
	
	public static void getScreenshot(WebDriver driver)
	{
		//DriverFactory driver = new DriverFactory();
		

		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		
		
		
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(screenshot, new File(outputFolder+"/Screenshot_"+counter+".png"));
			counter++;
			logger.info(" Taken Screenshot. File name: Screenshot_"+counter);
	}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(" Couldn't Taken Screenshot. File name: ");
		}
	}
	
	
		public static void getScreenshot(WebDriver driver,String filename)
		{
			//DriverFactory driver = new DriverFactory();
			

			//WebDriverManager.chromedriver().setup();
			//WebDriver driver = new ChromeDriver();
			
			//driver.get("https://www.google.com/?gws_rd=ssl");
			
			
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			try {
				FileUtils.copyFile(screenshot, new File(outputFolder+"/Screenshot_"+filename+"_"+counter+".png"));
				counter++;
				logger.info(" Taken Screenshot. File name: Screenshot_" +filename+"_"+counter);
		}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(" Couldn't Taken Screenshot. File name: ");
			}

}
}
