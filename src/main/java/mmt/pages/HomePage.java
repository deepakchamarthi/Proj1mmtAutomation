package mmt.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.List;

import mmt.bo.HomePageBO;
import mmt.filereaders.ConfigPropertiesReader;

import mmt.utilities.ScreenshotUtil;

public class HomePage extends BasePage {

	// protected WebDriver driver;
	Logger logger = LogManager.getLogger(HomePage.class);

	// Reading url from config page
	private String pageURL = ConfigPropertiesReader
			.getValueFromConfigFile(ConfigPropertiesReader.integration_envFilePath, "baseUrl") + "/hotels";

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
	public By childAge = By.cssSelector("[data-cy=childAge-1]");

	// Creating object for BasePage with 15sec wait
	//BasePage base = new BasePage(15);
	
	

	// Creating object of HomePageBO to set and get data
	HomePageBO home = new HomePageBO();

	public void setHomePageBO() {

		home.setCityName("Chennai");

		home.setTotalAdults(4);
		home.setTotalChildren(3);

		List<Integer> childrenAge = new ArrayList<Integer>();
		childrenAge.add(2);
		childrenAge.add(9);
		childrenAge.add(5);

		home.setChildrenAge(childrenAge);
		home.setCheckInDate("25");
		home.setCheckInMonth("Sep");
		home.setCheckInYear("2021");

		home.setCheckOutDate("30");
		home.setCheckOutMonth("Sep");
		home.setCheckOutYear("2021");

	}

//Open the URL
	public void open() {
		openPage(pageURL);

	}

	/**
	 * Selects city based on the name provided as param
	 * 
	 * @param by    element to locate
	 * @param value city value
	 */
	public void selectCity(By by) {
		String cityName = home.getCityName();
		try {
			waitForElementToBeVisible(by);

			WebElement city = driver.findElement(By.id("city"));
			city.sendKeys(cityName);

			logger.info("Sent City name as:" + cityName);
			WebElement cityList = waitForElementToBeVisible(citymenu_Text);

			cityList.sendKeys(cityName);
			waitForElementToBeVisible(firstOptionCity);
//Using fluent time to wait until text is filled properly.
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

			wait.until(ExpectedConditions.textToBePresentInElementLocated(firstOptionCity, cityName));
			cityList.sendKeys(Keys.ARROW_DOWN);
			cityList.sendKeys(Keys.RETURN);
			logger.info("city selected");
		}

		catch (Exception e) {

			WebElement cityList = driver.findElement(citymenu_Text);
			waitForElementToBeVisible(firstOptionCity);
			cityList.sendKeys(Keys.ARROW_DOWN);
			cityList.sendKeys(Keys.RETURN);
			logger.info("Seems spelling mistake, First suggestion selected");
			ScreenshotUtil.getScreenshot(driver, "cityselection");
		}

	}


	/**
	 * Clicks checkin and checkout Date.
	 * 
	 * @param by takes element location
	 */

	public void clickOnChkInChekOut(By by) {

		WebElement checkin = driver.findElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(checkin).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.moveToElement(checkin).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		logger.info("clicked checkin");

		// Click on the date given as params.
		// Year, Month and Date -> All string for now
		clickondate(home.getCheckInYear(), home.getCheckInMonth(), home.getCheckInDate());

		clickondate(home.getCheckOutYear(), home.getCheckOutMonth(), home.getCheckOutDate());

	}

	/**
	 * 
	 * @param year  String to enter Year
	 * @param Month String to enter Month
	 * @param date  String to enter date
	 */

	// configurability for year date and month selection.
	// logic to add more configurability based on any year,data,month selection is
	// under developement.Refer mmt.pages/DateSetter.java class

	public void clickondate(String year, String Month, String date) {

		WebElement datePick = driver.findElement(By.cssSelector("[class=DayPicker-Day][aria-label*='" + year
				+ "'][aria-label*='" + Month + "'][aria-label*=' " + date + "']"));
		datePick.click();
		// System.out.println("Clicked on " + year + Month + date);
		logger.info("Clicked on " + year + Month + date);

	}

	/*
	 * 
	 * public void SelectGuestCountTemp(int adults, int children, String
	 * childrenAge) {
	 * 
	 * WebElement adultsCount = driver.findElement(By.cssSelector("[data-cy=adults-"
	 * + adults + "]")); adultsCount.click(); //
	 * System.out.println("clicked on Adults"); logger.info("clicked on Adults");
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * 
	 * WebElement ChildrenCount =
	 * driver.findElement(By.cssSelector("[data-cy=children-" + children + "]"));
	 * ChildrenCount.click(); // System.out.println("clicked children");
	 * logger.info("clicked children");
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * 
	 * for (int i = 0; i < children; i++) { WebElement selectAge =
	 * driver.findElement(By.cssSelector("[data-cy=childAge-" + i + "]"));
	 * selectAge.click(); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); Select select = new Select(selectAge); //
	 * select.selectByVisibleText("3"); select.selectByVisibleText(childrenAge); //
	 * WebElement selectChildAge = //
	 * driver.findElement(By.cssSelector("[data-cy='childAgeValue-3']")); //
	 * selectChildAge.click(); // System.out.println("Selected Children Age" + i);
	 * logger.info("Selected" + i + " Children Age as" + childrenAge);
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * ORIGNAL METHOD public void SelectGuestCount(int adults, int children) {
	 * 
	 * if (adults < 0 || children < 0) {
	 * logger.error("Select proper Adults and Children Count");
	 * 
	 * }
	 * 
	 * else {
	 * 
	 * try {
	 * 
	 * WebElement adultsCount = driver.findElement(By.cssSelector("[data-cy=adults-"
	 * + adults + "]")); adultsCount.click();
	 * 
	 * logger.info("Clicked on Adults");
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * 
	 * WebElement ChildrenCount =
	 * driver.findElement(By.cssSelector("[data-cy=children-" + children + "]"));
	 * ChildrenCount.click();
	 * 
	 * logger.info("Clicked on Children");
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * 
	 * //Loop to add Children age repeatedly for (int i = 0; i < children; i++) {
	 * WebElement selectAge = driver.findElement(By.cssSelector("[data-cy=childAge-"
	 * + i + "]")); selectAge.click(); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS);
	 * 
	 * selectChildrenAge(3,3,3,3);
	 * 
	 * Select select = new Select(selectAge); select.selectByVisibleText("3");
	 * 
	 * logger.info("Selected" + i + " Children Age as"); } }
	 * 
	 * catch (Exception e) { logger.info("Problem in selecting");
	 * e.printStackTrace(); ScreenshotUtil.getScreenshot(driver, "Guest Selection");
	 * } }
	 * 
	 * 
	 * 
	 * 
	 */

	/**
	 * 
	 * @param adults   Set Adults count
	 * @param children Set Children Count
	 * 
	 */

	public void SelectGuestCount() {

		int adults = home.getTotalAdults();
		if (adults < 0) {
			logger.error("Select proper Adults and Children Count");

		}

		else {

			try {

				WebElement adultsCount = driver.findElement(By.cssSelector("[data-cy=adults-" + adults + "]"));
				adultsCount.click();

				logger.info("Clicked on Adults");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				// Loop to add Children age repeatedly

			}

			catch (Exception e) {
				logger.info("Problem in selecting");
				e.printStackTrace();
				ScreenshotUtil.getScreenshot(driver, "Guest Selection");
			}
		}

	}

	public void setChildrenCount()

	{
		int childrenCount = home.getTotalChildren();
		List<Integer> childrenAge = home.getChildrenAge();

		WebElement ChildrenCount = driver.findElement(By.cssSelector("[data-cy=children-" + childrenCount + "]"));
		ChildrenCount.click();

		logger.info("Clicked on Children");

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.visibilityOfElementLocated(childAge));
		for (int i = 0; i < childrenCount; i++) {

			WebElement selectAge = driver.findElement(By.cssSelector("[data-cy=childAge-" + i + "]"));
			selectAge.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			Select select = new Select(selectAge);

			select.selectByVisibleText(childrenAge.get(i).toString());
			logger.info("Selected" + i + " Children Age as " + childrenAge.get(i).toString());
		}

	}

	public List<Integer> selectChildAge() {

		List<Integer> cAge = home.getChildrenAge();
		// List<Integer> childrenAge = new ArrayList<Integer>();
		cAge.add(2);
		cAge.add(5);
		cAge.add(3);

		setChildrenCount();
		return cAge;

	}

	public void selectChildrenAge(int child1Age, int child2Age, int child3Age) {
		int totalChildren = home.getTotalChildren();

		String childrenAge[] = { String.valueOf(child1Age), String.valueOf(child2Age), String.valueOf(child3Age) };
		for (int i = 0; i < totalChildren; i++) {

			WebElement selectAge = driver.findElement(By.cssSelector("[data-cy=childAge-" + i + "]"));
			selectAge.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			Select select = new Select(selectAge);

			select.selectByVisibleText(childrenAge[i]);
			logger.info("Selected" + i + " Children Age as " + childrenAge[i]);

		}
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

		// WebDriverWait wait = new WebDriverWait(driver, 20, 200);

		// WebElement checkinButton =
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//label[@for='checkin']//span[2]"))));

		waitForElementToBeVisible(travelFor_Text);

		// WebElement travel = wait
		// .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[data-cy*=travelForText]"))));

		click(travelFor_Text);

		logger.info("Clicked on TravelFor");

		waitForElementToBeVisible(travelReason_Text);
		WebElement travelReason = driver.findElement(travelReason_Text);

		logger.info("Clicked on Leisure");
		travelReason.click();

	}

	public void submit() {
		waitForElementToBeVisible(search_Button);
		WebElement submit = driver.findElement(By.cssSelector("[data-cy='submit']"));
		// System.out.println("Clicked on submit");
		logger.info("Clicked on Submit");
		submit.click();
		ScreenshotUtil.getScreenshot(driver, "HomePage");

	}
}
