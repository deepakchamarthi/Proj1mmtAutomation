package mmt.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mmt.utilities.ScreenshotUtil;

public class RoomSelectionPage extends BasePage {

	Logger logger = LogManager.getLogger(SearchPage.class);

	public static By rooms_Link = By.cssSelector("#detpg_hotel_rooms");
	public static By check_count = By.cssSelector("[class*='comboTitle']");
	public static By rooom_Header = By.cssSelector("[class='ddHeaderTitle']");
	public static By guestCount1 = By.cssSelector("[value='2 Adults 1 Child'] ");
	public static By guestCount2 = By.cssSelector("[value='2 Adults 2 Children'] ");
	public static By addRooms_Button = By.cssSelector("#detpg_multi_2_add_room");
	public static By bookCmbo_Button = By.cssSelector("#detpg_book_combo_btn");
	public static By pickAnotherDate_Button = By.cssSelector("#alternateDates_details");
	
	

	BasePage base = new BasePage(15);

	public void clickRooms() {
		
		try
		{
			
		
        base.waitForElementToBeVisible(rooms_Link);
		base.click(rooms_Link);

		logger.info("opened Rooms link");
		}
		catch(Exception e)
		{
			//base.waitForElementToBeVisible(pickAnotherDate_Button);
			logger.error("Hotel Completely Booked. Please Start over by changing Dates");
		}

	}

	public void getValue() {
		
		WebElement ele = driver.findElement(By.id("guest"));
		String innerHTML = ele.getAttribute("innerHTML");
		
		logger.info("Value is " + innerHTML);

	}

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

				if (valueCounter== 1) {
					
										
					logger.info("Button clicked");
					break;
				}
				else
				{
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
			}
			else
			{
				logger.info("Button not clicked");
			}
		}

	}
	
	public void bookCombo()
	{
		
	try
	{
		int counter =0;
		base.waitForElementToBeVisible(bookCmbo_Button);
		List<WebElement> buttonCombos = driver.findElements(bookCmbo_Button);
		
		for (WebElement button : buttonCombos) {
		counter++;
		if(counter==1)
		{
		    button.click();
		    logger.info("combo Button clicked");
		    break;
	}
	
		else
		{
			continue;
		}
		
	}
	}
	catch(Exception e)
	{
		try
		{
			WebElement comboButton = driver.findElement(bookCmbo_Button);
			comboButton.click();
			ScreenshotUtil.getScreenshot(driver, "RoomSelectionPage");
		}
		catch(Exception e1)
		{
			logger.error("Hotel Completely Booked. Please Start over by changing Dates");
		}
		
		//base.waitForElementToBeVisible(pickAnotherDate_Button);
		logger.error("Hotel Completely Booked. Please Start over by changing Dates");
		ScreenshotUtil.getScreenshot(driver, "RoomSelectionPage");
	}
}
	
}



