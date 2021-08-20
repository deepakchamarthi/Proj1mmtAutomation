package mmt.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mmt.bo.HomePageBO;
import mmt.utilities.ScreenshotUtil;

public class RoomSelectionPage extends BasePage {

	Logger logger = LogManager.getLogger(RoomSelectionPage.class);
//Page element locators
	public static By rooms_Link = By.cssSelector("#detpg_hotel_rooms");
	public static By check_count = By.cssSelector("[class*='comboTitle']");
	public static By rooom_Header = By.cssSelector("[class='ddHeaderTitle']");
	public static By guestCount1 = By.cssSelector("[value='2 Adults 1 Child'] ");
	public static By guestCount2 = By.cssSelector("[value='2 Adults 2 Children'] ");
	public static By addRooms_Button = By.cssSelector("#detpg_multi_2_add_room");
	public static By bookCmbo_Button = By.cssSelector("#detpg_book_combo_btn");
	public static By roomSuggetion = By.cssSelector("[class='_RoomType'] ");
	public static By guestText = By.cssSelector("[class*='comboTitle'] ");

	public static By pickAnotherDate_Button = By.cssSelector("#alternateDates_details");

//Base page Obj wait 15sec
	BasePage base = new BasePage(15);

	// Clicking Rooms link in the page
	public void clickRooms() {

		try {

			base.waitForElementToBeVisible(rooms_Link);
			base.click(rooms_Link);

			logger.info("opened Rooms link");
		} catch (Exception e) {
			// base.waitForElementToBeVisible(pickAnotherDate_Button);
			logger.error("Hotel Completely Booked. Please Start over by changing Dates");
		}

	}

	// Get the values of guest info
	// There is a pending implementation to extract the data of guests count and
	// assert with the input guest count

	public void getValue() {

		WebElement ele = driver.findElement(By.id("guest"));
		String innerHTML = ele.getAttribute("innerHTML");

		logger.info("Value is " + innerHTML);

	}

	// To verify guest count from the sugggestions.

	public void verifyGuestCountFromRooms()

	{
		// Setting up Adults & children count using HomePageBO class
		HomePageBO home = new HomePageBO();
		home.setTotalAdults(4);
		home.setTotalChildren(3);

		try
		{
			
		
		// Collecting the suggestion into the list
		// Not using this list anywhere. But planned to use it for future purpose if we
		// need to find some other text too
		base.waitForElementToBeVisible(guestText);
		List<WebElement> suggestions = driver.findElements(guestText);
		boolean flag;

		int counter = 0;
		List<String> suggesstionsList = new ArrayList<String>();
		for (WebElement suggestion : suggestions) {
			counter++;

			// WebElement roomText = driver.findElement(By.xpath("//p[text()='Adults']"));
			// String roomTextValue = roomText.getText();
			// p[text()='Room']

			String roomSuggestion = suggestion.getText();
			suggesstionsList.add(roomSuggestion);

			//Checking if the text contains given adults and children count
			flag = roomSuggestion.contains(
					"Recommended for " + home.getTotalAdults() + " Adults & " + home.getTotalChildren() + " Children");
			if (flag == true) {
				logger.info(" Room#" + counter
						+ " Adults and Children count is matching with the input.Total Adults Count: "
						+ home.getTotalAdults() + " & Total Children Count: " + home.getTotalChildren());

			} else
				logger.error(" Room#" + counter + " Adults and Children count not matching with input Adults: "
						+ home.getTotalAdults() + " & Total Children Count: " + home.getTotalChildren());

		}
		}

		catch(Exception e)
		{
			
			logger.error("Problem with getting total adults/children count from the text. Continuing");
			 
			e.printStackTrace();
			
		}
	}

	// To verify the test of the guest count.
	public void VerifyText()

	{

		WebDriverWait wait = new WebDriverWait(driver, 60, 200);
		WebElement comboTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(check_count));
		base.waitForElementToBeVisible(check_count);
		base.getText(check_count);
		logger.info("Guest Count Text:");

		List<WebElement> searchList = driver.findElements(check_count);
		int counter = 0;
		for (WebElement searchResult : searchList) {
			counter++;
			if (counter == 1) {
				String guestCount = searchResult.findElement(check_count).getText();
				logger.info("Guest Count Text: " + guestCount);

				break;
			}
		}
	}

	// Logic to select the room based on children and adult count displayed
	public void click2audlts1ChildButton() {

		base.waitForElementToBeVisible(rooom_Header);
		List<WebElement> roomsList = driver.findElements(rooom_Header);
		logger.info("Inside click2audlts1ChildButton");

		int valueCounter = 0;
		int value2Counter = 0;
		for (WebElement room : roomsList) {
			List<WebElement> values = driver.findElements(guestCount1);

			for (WebElement value : values)
				valueCounter++;

			{
				base.click(addRooms_Button);

				if (valueCounter == 1) {

					logger.info("Button clicked");
					break;
				} else {
					logger.info("Button not clicked");
				}

			}

			List<WebElement> values2 = driver.findElements(guestCount2);

			for (WebElement value2 : values2)
				value2Counter++;
			if (value2Counter == 1) {
				base.click(addRooms_Button);

				logger.info("Button2 clicked");
				break;
			} else {
				logger.info("Button not clicked");
			}
		}

	}

	// booking button
	public void bookCombo() {

		try {
			int counter = 0;
			base.waitForElementToBeVisible(bookCmbo_Button);

			logger.info("bookCmbo_Button found");
			List<WebElement> buttonCombos = driver.findElements(bookCmbo_Button);

			for (WebElement button : buttonCombos) {
				counter++;
				if (counter == 1) {
					logger.info(counter);
					button.click();

					logger.info("combo Button clicked");
					break;
				}

				else {
					button.click();
					continue;
				}

			}
		} catch (Exception e) {
			try {
				WebElement comboButton = driver.findElement(bookCmbo_Button);
				comboButton.click();
				ScreenshotUtil.getScreenshot(driver, "RoomSelectionPage");
				logger.info("catch ->try");
			} catch (Exception e1) {
				logger.error("Hotel Completely Booked. Please Start over by changing Dates");
			}

			// base.waitForElementToBeVisible(pickAnotherDate_Button);
			logger.error("Hotel Completely Booked. Please Start over by changing Dates");
			ScreenshotUtil.getScreenshot(driver, "RoomSelectionPage");
		}
	}

}
