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
import mmt.bo.PageURL;
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
	public static By addFirstRoom = By.cssSelector("rmPayable__dtl--addBtn");
	public static By reviewSelection = By.cssSelector("#detpg_confirm_booking_btn");
	public static By selectRoom = By.xpath("//p[text()='SELECT ROOM']");

	public static By pickAnotherDate_Button = By.cssSelector("#alternateDates_details");



	// Clicking Rooms link in the page
	public void clickRooms() {

		try {

			waitForElementToBeVisible(rooms_Link);
			click(rooms_Link);

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

		try {

			// Collecting the suggestion into the list
			// Not using this list anywhere. But planned to use it for future purpose if we
			// need to find some other text too
			waitForElementToBeVisible(guestText);
			List<WebElement> suggestions = driver.findElements(guestText);
			boolean flag;

			int counter = 0;
			List<String> suggesstionsList = new ArrayList<String>();
			for (WebElement suggestion : suggestions) {
				counter++;

				String roomSuggestion = suggestion.getText();
				suggesstionsList.add(roomSuggestion);

				// Checking if the text contains given adults and children count
				flag = roomSuggestion.contains("Recommended for " + home.getTotalAdults() + " Adults & "
						+ home.getTotalChildren() + " Children");
				if (flag == true) {
					logger.info(" Room#" + counter
							+ " Adults and Children count is matching with the input.Total Adults Count: "
							+ home.getTotalAdults() + " & Total Children Count: " + home.getTotalChildren());

				} else
					logger.error(" Room#" + counter + " Adults and Children count not matching with input Adults: "
							+ home.getTotalAdults() + " & Total Children Count: " + home.getTotalChildren());

			}
		}

		catch (Exception e) {

			logger.error("Problem with getting total adults/children count from the text. Continuing");

			e.printStackTrace();

		}
	}

	// To verify the test of the guest count.
	public void VerifyText()

	{

		WebDriverWait wait = new WebDriverWait(driver, 60, 200);
		WebElement comboTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(check_count));
		waitForElementToBeVisible(check_count);
		getText(check_count);
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

		waitForElementToBeVisible(rooom_Header);
		List<WebElement> roomsList = driver.findElements(rooom_Header);
		logger.info("Inside click2audlts1ChildButton");

		int valueCounter = 0;
		int value2Counter = 0;
		for (WebElement room : roomsList) {
			List<WebElement> values = driver.findElements(guestCount1);

			for (WebElement value : values)
				valueCounter++;

			{
				click(addRooms_Button);

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
				click(addRooms_Button);

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

			List<WebElement> buttonCombos = driver.findElements(bookCmbo_Button);
			List<WebElement> selectRoomsCombo = driver.findElements(selectRoom);

//clicks on combo box
			for (WebElement button : buttonCombos) {
				counter++;

				if (button.isDisplayed()) {
					logger.info(counter);
					button.click();
					logger.info("combo Button clicked");
					waitForPageLoad();
					// Fail safe mechanism to open url to continue flow in case combo button not
					// found.
					confirmURL();
					break;
				}

				else {

					for (WebElement select : selectRoomsCombo) {

						if (select.isDisplayed()) {
							select.click();
							logger.info("clicked Select combo");
						}

						else {
							selectAddcombo();
							logger.info("select combo Button not found.Adding combos");
						}
					}

					break;
				}

			}
		} catch (Exception e) {

			logger.error("Hotel Completely Booked. Please Start over by changing Dates");
			confirmURL();
		}

		ScreenshotUtil.getScreenshot(driver, "RoomSelectionPage");

	}

//to check if expected page is loaded	
	private void confirmURL() {
		String currentURL = driver.getCurrentUrl();
		if ((currentURL.contains("selectRoom"))) {
			// base.openPage(PageURL.reviewBookingURL);
			logger.error("right page opened already");

		} else {
			openPage(PageURL.reviewBookingURL);
			logger.info("Page opened?");

		}

	}

	// method to select Add combo box
	// Someitmes Selection box is not visible, so Else condition comes here
	private void selectAddcombo() {

		try {

			List<WebElement> addCombos = driver.findElements(addFirstRoom);
			for (WebElement add : addCombos) {

				if (add.isDisplayed()) {
					add.click();
					logger.info("Add room Button clicked");

					click(reviewSelection);

					driver.switchTo().alert().accept();
					logger.info("switched to alert");

					driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

					logger.info("clicked on alert");

				} else {
					logger.info("Unable to select AddCombo");
					confirmURL();
				}

			}

		} catch (Exception e) {

			logger.info("Problem in room selection");
			openPage(PageURL.reviewBookingURL);

		}

	}
}
