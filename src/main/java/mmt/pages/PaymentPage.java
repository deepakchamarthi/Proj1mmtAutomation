package mmt.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import mmt.utilities.ScreenshotUtil;

public class PaymentPage extends BasePage {

	Logger logger = LogManager.getLogger(PaymentPage.class);

	public static By details = By.cssSelector("[class='make-flex hrtl-center']");

	BasePage base = new BasePage(15);

	public void getDetails()

	{

		try
		{
			
		
		base.waitForElementToBeVisible(details);

		String innerHTML = driver.findElement(details).getAttribute("innerHTML");

		String checkOutDate = innerHTML.substring(523, 537);
		String checkInDate = innerHTML.substring(197, 211);

		logger.info("Check-in Date : " + checkInDate + "Check-Out Date: " + checkOutDate);
		ScreenshotUtil.getScreenshot(driver, "PayementPage");
		}
		
		catch(Exception e)
		{
			
			logger.error("problem with getting details");
		}

	}

}
