package mmt.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import mmt.bo.FillGuestDetailsBO;
import mmt.utilities.ScreenshotUtil;
import mmt.utilities.TestDataGenerator;

public class ReviewPage  extends BasePage{

	Logger logger = LogManager.getLogger(SearchPage.class);

	//Page elements locators 
	public static By firstName_Text = By.cssSelector("#fName");
	public static By lastName_Text = By.cssSelector("#lName");
	public static By email_Text = By.cssSelector("#email");
	public static By mobile_Text = By.cssSelector("#mNo");
	public static By pay_Button = By.cssSelector("[class*='ContinuePayment']");

	//Base page Obj with 15mins wait
	BasePage base = new BasePage(15);
	

	
	/**
	 * 
	 * @param guestInfo 
	 */
	//Fills the guest info details using Faker data
	public void fillDetails(FillGuestDetailsBO guestInfo) {

		TestDataGenerator.generateGuestData(guestInfo);

		try {
			base.waitForPageLoad();
			base.waitForElementToBeVisible(firstName_Text);
			base.fillText(firstName_Text, guestInfo.getFirstName());
			base.fillText(lastName_Text, guestInfo.getLastName());
			base.fillText(email_Text, guestInfo.getEmail());
			base.fillText(mobile_Text, guestInfo.getMobile());
			logger.info("Data entered ");

		}

		catch (Exception e) {
			e.printStackTrace();
			logger.error("problem with filling data. Please check the input data");
		}

	}

	//Method to clickPay button
	public void clickPay() {

		try {
			base.click(pay_Button);
			logger.info("Clicked Pay");
			ScreenshotUtil.getScreenshot(driver, "ReviewPage");
		}

		catch (Exception e) {

			logger.error("problem with clicking on Pay");
			ScreenshotUtil.getScreenshot(driver, "ReviewPage");
		}

	}

}
