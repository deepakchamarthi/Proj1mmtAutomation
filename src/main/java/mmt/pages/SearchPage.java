package mmt.pages;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.StarRating;

public class SearchPage extends BasePage {

	Logger logger = LogManager.getLogger(SearchPage.class);

	public static By hotel_List = By.cssSelector("[class*='listingRow ']");
	public static By price_Selector = By.cssSelector("[role='slider'][aria-valuemin='0']");
	public static By rating_Checkbox4 = By.xpath("//p[text()='4 Star']");
	public static By rating_Checkbox3 = By.xpath("//p[text()='3 Star']");
	public static By rating_Checkbox2 = By.xpath("//p[text()='2 Star']");
	public static By rating_Checkbox1 = By.xpath("//p[text()='1 Star']");
	public static By rating_Checkbox5 = By.xpath("//p[text()='5 Star']");
	public static By Viewmore_Link = By.xpath("//div[@id='STAR_CATEGORY']//span[@id='hlistpg_proptypes_show_more']");

	BasePage base = new BasePage(15);

	public void scrollControl(int min, int max, By by) {

		base.waitForElementToBeVisible(by);
		WebElement price_slider = driver.findElement(by);

		price_slider.click();

		logger.info("Clicked on the slider");

		base.waitForPageLoad();

		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(price_slider, min, max).build();
		action.perform();

		logger.info("Moved the slider");

	}

	public void startRating(StarRating sRate) {

		try {

			base.waitForElementToBeVisible(rating_Checkbox4);
			WebElement rating = driver.findElement(rating_Checkbox4);
			// rating.click();

			switch (sRate) {

			case RATING1:
				base.waitForElementToBeVisible(Viewmore_Link);
				base.click(Viewmore_Link);
				base.waitForElementToBeVisible(rating_Checkbox1);
				base.click(rating_Checkbox1);
				logger.info("Clicked on 1 Star ");
				break;
			case RATING2:
				base.waitForElementToBeVisible(Viewmore_Link);
				base.click(Viewmore_Link);
				base.waitForElementToBeVisible(rating_Checkbox2);
				base.click(rating_Checkbox2);
				logger.info("Clicked on  2 Star ");
				break;
			case RATING3:
				base.waitForElementToBeVisible(Viewmore_Link);
				base.click(Viewmore_Link);
				base.waitForElementToBeVisible(rating_Checkbox3);
				base.click(rating_Checkbox3);
				logger.info("Clicked on  3 Star ");
				break;
			case RATING4:

				base.click(rating_Checkbox4);
				logger.info("Clicked on  4 Star ");
				break;
			case RATING5:
				base.click(rating_Checkbox5);
				logger.info("Clicked on  4 Star ");
				break;
			default:
				base.click(rating_Checkbox4);
				logger.info("Clicked on  4 Star default ");
				break;

			}
		}

		// logger.info("Clicked on the 4 Star (Try)");

		catch (Exception e) {
			try {
				base.click(rating_Checkbox4);

				logger.info("Clicked on the 4 Star (Catch)");
			} catch (Exception e1) {

				logger.info("Problem in filtering ratings. Moving on");
				e1.printStackTrace();

			}
		}

	}

	public void selectHotel(By by, int hotelPosition) {
		// WebDriverWait wait = new WebDriverWait(driver, 10, 200);

		// WebElement searchBox =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		try {

			base.waitForElementToBeVisible(by);
			List<WebElement> searchList = driver.findElements(by);
			int counter = 0;
			int maxHotelsInList = getNoOfHotels(by);
			logger.info("Number of hotels found: " + maxHotelsInList);

			for (WebElement hotel : searchList) {
				counter++;

				if (maxHotelsInList == 0) {

					logger.info("No hotels found: " + counter);
					selectHotel(by, hotelPosition);
				}

				else if (counter == maxHotelsInList) {
					hotel.click();

					logger.info("clicked on Hotel number: " + counter + "from the list of  " + maxHotelsInList
							+ " Hotels (elf1)");

				}

				else if (counter == hotelPosition) {

					hotel.click();
					logger.info("clicked on Hotel number: " + hotelPosition + "from the list of  " + maxHotelsInList
							+ " Hotels (elf2)");
					break;
				}

				else {

					continue;
				}

			}
		}

		catch (Exception e) {
			logger.info("No hotels found: " + getNoOfHotels(by));
		}

	}

	public int getNoOfHotels(By by) {

		int hotelCounter = 0;
		base.waitForElementToBeVisible(by);
		List<WebElement> searchList = driver.findElements(by);

		for (WebElement hotel : searchList) {
			hotelCounter++;
		}

		return hotelCounter;

	}

	public void switchToNewTab() {

		try {

			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			allWindows.remove(parentWindow);

			for (String windowId : allWindows) {
				driver.switchTo().window(windowId);
				logger.info("switched to new tab");
			}
		} catch (Exception e) {
			logger.info("Canot switch to new tab");
		}
	}

}
