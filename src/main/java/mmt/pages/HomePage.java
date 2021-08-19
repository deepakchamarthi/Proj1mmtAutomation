package mmt.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import mmt.filereaders.ConfigPropertiesReader;
import mmt.filereaders.ConfigReader;
import mmt.utilities.ScreenshotUtil;


public class HomePage extends BasePage {

	// protected WebDriver driver;
	Logger logger = LogManager.getLogger(HomePage.class);

	// FIXTHIS
	// private String pageURL = ConfigReader.getProperty("baseUrl")+"/hotels";
	
	private String pageURL = ConfigPropertiesReader.getValueFromConfigFile(ConfigPropertiesReader.integration_envFilePath,"baseUrl")+"/hotels";

	// Hardcoding for now

	//private String pageURL = "https://www.makemytrip.com/hotels/";

	/*
	 * public HomePage(WebDriver driver) { super.driver = driver; }
	 */
	// page locators

	public By city_Text = By.id("city");
	public By citymenu_Text = By.cssSelector("[placeholder*='city']");
	public By checkIn_button = By.id("checkin");
	public By checkOut_Button = By.id("checkout");
	public By travelFor_Text = By.cssSelector("[data-cy*=travelForText]");
	public By guest_Button = By.cssSelector("#guest");
	public By guest_ButtonId = By.id("guest");

	public By apply_Button = By.cssSelector("[data-cy='submitGuest']");
	public By travelReason_Text = By.cssSelector("[data-cy='travelFor-Leisure']");
	public By search_Button = By.cssSelector("[data-cy='submit']");
	public By firstOptionCity = By.cssSelector("[role='option'][id*='item-0']");

	BasePage base = new BasePage(15);

	public void open() {
		openPage(pageURL);
	}

	public void selectCity(By by, String value)  {

		try
		{
			base.waitForElementToBeVisible(by);
		
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys(value);
				
		logger.info("Sent City name as:" + value);
		WebElement cityList = base.waitForElementToBeVisible(citymenu_Text);
				
		cityList.sendKeys(value);
		base.waitForElementToBeVisible(firstOptionCity);
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(firstOptionCity, value));   
		cityList.sendKeys(Keys.ARROW_DOWN);
		cityList.sendKeys(Keys.RETURN);
		logger.info("city selected");
		}
		
		catch(Exception e)
		{
			
			WebElement cityList = driver.findElement(citymenu_Text);
			base.waitForElementToBeVisible(firstOptionCity);
			cityList.sendKeys(Keys.ARROW_DOWN);
			cityList.sendKeys(Keys.RETURN);
			logger.info("Seems spelling mistake, First suggestion selected");
		}
		
	}

	public void clickOnChkInChekOuttemp() {

		try {
			base.waitForElementToBeVisible(checkIn_button);
			WebElement checkin = driver.findElement(By.id("checkin"));

			Actions actions = new Actions(driver);

			actions.moveToElement(checkin).click().perform();

			actions.moveToElement(checkin).click().perform();
			logger.info("Clicked on Checkin Date");
//		System.out.println("clicked");

			clickondate("2021", "Aug", "25");
			clickondate("2021", "Aug", "31");
		} catch (Exception e) {
			logger.error("Exception Occured");
			e.printStackTrace();
			ScreenshotUtil.getScreenshot(driver, "CheckinFailed");
		}

	}

	public void clickOnChkInChekOut(By by) {

		// WebElement checkin = waitForElementToBeVisible(by);

		WebElement checkin = driver.findElement(by);
		// pass city_Text

		Actions actions = new Actions(driver);

		actions.moveToElement(checkin).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.moveToElement(checkin).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// System.out.println("clicked checkin");
		logger.info("clicked checkin");
		// Thread.sleep(2000);

		clickondate("2021", "Aug", "25");

		clickondate("2021", "Aug", "31");

	}

	public void clickondate(String year, String Month, String date) {

		WebElement datePick = driver.findElement(By.cssSelector("[class=DayPicker-Day][aria-label*='" + year
				+ "'][aria-label*='" + Month + "'][aria-label*=' " + date + "']"));
		datePick.click();
		// System.out.println("Clicked on " + year + Month + date);
		logger.info("Clicked on " + year + Month + date);

	}

	public void SelectGuestCountTemp(int adults, int children, String childrenAge) throws InterruptedException {

		WebElement adultsCount = driver.findElement(By.cssSelector("[data-cy=adults-" + adults + "]"));
		adultsCount.click();
		// System.out.println("clicked on Adults");
		logger.info("clicked on Adults");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement ChildrenCount = driver.findElement(By.cssSelector("[data-cy=children-" + children + "]"));
		ChildrenCount.click();
		// System.out.println("clicked children");
		logger.info("clicked children");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		for (int i = 0; i < children; i++) {
			WebElement selectAge = driver.findElement(By.cssSelector("[data-cy=childAge-" + i + "]"));
			selectAge.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Select select = new Select(selectAge);
			// select.selectByVisibleText("3");
			select.selectByVisibleText(childrenAge);
			// WebElement selectChildAge =
			// driver.findElement(By.cssSelector("[data-cy='childAgeValue-3']"));
			// selectChildAge.click();
			// System.out.println("Selected Children Age" + i);
			logger.info("Selected" + i + " Children Age as" + childrenAge);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

	}

	public void SelectGuestCount(int adults, int children) {

		if (adults < 0 || children < 0) {
			logger.error("Select proper Adults and Children Count");

		}

		else {

			try {

				WebElement adultsCount = driver.findElement(By.cssSelector("[data-cy=adults-" + adults + "]"));
				adultsCount.click();
				// System.out.println("clicked on Adults");
				logger.info("Clicked on Adults");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				WebElement ChildrenCount = driver.findElement(By.cssSelector("[data-cy=children-" + children + "]"));
				ChildrenCount.click();
				// System.out.println("clicked children");
				logger.info("Clicked on Children");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				for (int i = 0; i < children; i++) {
					WebElement selectAge = driver.findElement(By.cssSelector("[data-cy=childAge-" + i + "]"));
					selectAge.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Select select = new Select(selectAge);
					select.selectByVisibleText("3");
					// WebElement selectChildAge =
					// driver.findElement(By.cssSelector("[data-cy='childAgeValue-3']"));
					// selectChildAge.click();
					// System.out.println("Selected Children Age" + i);
					logger.info("Selected" + i + " Children Age as");
				}
			}

			catch (Exception e) {
				logger.info("Problem in selecting");
				e.printStackTrace();
			}
		}

	}

	public void clickOnguestsTemp() {

		WebElement guest = driver.findElement(By.id("guest"));

		Actions actions = new Actions(driver);

		actions.moveToElement(guest).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.moveToElement(guest).click().perform();
		// System.out.println("clicked on Guest");
		logger.info("Clicked on Guest");

	}

	public void clickOnguests(By by) {

		WebElement guest = driver.findElement(by);

		Actions actions = new Actions(driver);

		actions.moveToElement(guest).click().perform();
		// Thread.sleep(3000);
		actions.moveToElement(guest).click().perform();
		// System.out.println("clicked on Guest");
		logger.info("clicked on Guest");

	}

	public void selectApply(By by) {

		WebElement apply = waitForElementToBeVisible(by);
		apply.click();
		// System.out.println("Clicked on Apply");
		logger.info("Clicked on Apply");
	}

	public void travelReson() {

		//WebDriverWait wait = new WebDriverWait(driver, 20, 200);

		// WebElement checkinButton =
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//label[@for='checkin']//span[2]"))));

		base.waitForElementToBeVisible(travelFor_Text);

		// WebElement travel = wait
		// .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[data-cy*=travelForText]"))));

		base.click(travelFor_Text);

		logger.info("Clicked on TravelFor");

		base.waitForElementToBeVisible(travelReason_Text);
		WebElement travelReason = driver.findElement(travelReason_Text);

		logger.info("Clicked on Leisure");
		travelReason.click();

	}

	public void submit() {
		base.waitForElementToBeVisible(search_Button);
		WebElement submit = driver.findElement(By.cssSelector("[data-cy='submit']"));
		// System.out.println("Clicked on submit");
		logger.info("Clicked on Submit");
		submit.click();
		ScreenshotUtil.getScreenshot(driver, "HomePage");

	}
}
