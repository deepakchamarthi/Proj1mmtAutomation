package mmt.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import mmt.utilities.ScreenshotUtil;

public class PaymentPage extends BasePage {

	Logger logger = LogManager.getLogger(PaymentPage.class);

	public static By details = By.cssSelector("[class='make-flex hrtl-center']");

	// Basepage object with wait time 15sec
	BasePage base = new BasePage(15);
	

	// To get details from the confirmation text.
	// Reading checkin and checkout dates.

	// Getdetails of the guests checkin time to test with assertions.
	// Returns the list of checkin and chkeckout dates - useful to compare with BO
	// checkin checkout dates
	public List<String> getDetails()

	{
		List<String> checkInOutDates = new ArrayList<String>();

		try {

			base.waitForElementToBeVisible(details);

			String innerHTML = driver.findElement(details).getAttribute("innerHTML");

			String checkOutDate = innerHTML.substring(523, 537);
			String checkInDate = innerHTML.substring(197, 211);

			checkInOutDates.add(checkInDate);
			checkInOutDates.add(checkOutDate);

			logger.info("Check-in Date : " + checkInDate + "Check-Out Date: " + checkOutDate);
			ScreenshotUtil.getScreenshot(driver, "PayementPage");

			return checkInOutDates;

		}

		catch (Exception e) {

			logger.error("problem with getting details");
			return checkInOutDates;
		}

	}

}
