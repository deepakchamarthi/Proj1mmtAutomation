package mmt.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mmt.drivermanager.DriverFactory;

public class BasePage {

	protected WebDriver driver;
	public WebDriverWait wait;

	private Logger logger= LogManager.getLogger(BasePage.class);

	//constructor 
	public BasePage() {
		this.driver = DriverFactory.getCurrentDriver();
		wait = new WebDriverWait(this.driver, 5);
	}

	//constructor with wait time.
	public BasePage(int waitTime) {
		this();
		wait = new WebDriverWait(this.driver, waitTime);
	}

	//method to Open url and maximize the browser
	public void openPage(String url) {
		logger.info("opening the url" + url);
		driver.get(url);
		//added now
		driver.manage().window().maximize();
	}

	
	/**
	 * 
	 * @param by Element
	 * @param value 
	 */
	//method to fill the text with value By given element location
	
	public void fillText(By by, String value) {
		WebElement ele = waitForElementToBeVisible(by);
		ele.clear();
		ele.sendKeys(value);
	}

	//Method to click on the element by location
	public void click(By by) {
		WebElement ele = waitForElementToBeVisible(by);
		ele.click();
	}

	//Method to slide the element by position 
	public void slider(By by) {
		WebElement ele = waitForElementToBeVisible(by);
		Actions move = new Actions(driver);

		Action action = (Action) move.dragAndDropBy(ele, 30, 0).build();
		action.perform();
	}
	//Method to slide the element by arrrow
	public void slidebyArrow(By by) {
		WebElement ele = waitForElementToBeVisible(by);

		for (int i = 0; i < 10; i++) {
			ele.sendKeys(Keys.ARROW_RIGHT);
		}

	}

	//Method to slide the element by arrrow
	protected String getText(By by) {
		return waitForElementToBeVisible(by).getText();
	}

	//Method make the page wait until it loads completely
	protected void waitForPageLoad() {
		((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}
	//Method make the elemt wait until it is available
	public WebElement waitForElementToBeVisible(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	//Method get current url
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	//Method get driver title
	protected String getTitle() {
		return driver.getTitle();
	}
}
