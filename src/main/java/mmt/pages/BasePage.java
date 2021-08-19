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

	private Logger logger= LogManager.getLogger(HomePage.class);

	public BasePage() {
		this.driver = DriverFactory.getCurrentDriver();
		wait = new WebDriverWait(this.driver, 5);
	}

	public BasePage(int waitTime) {
		this();
		wait = new WebDriverWait(this.driver, waitTime);
	}

	public void openPage(String url) {
		logger.info("opening the url" + url);
		driver.get(url);
		//added now
		driver.manage().window().maximize();
	}

	public void fillText(By by, String value) {
		WebElement ele = waitForElementToBeVisible(by);
		ele.clear();
		ele.sendKeys(value);
	}

	public void click(By by) {
		WebElement ele = waitForElementToBeVisible(by);
		ele.click();
	}

	public void slider(By by) {
		WebElement ele = waitForElementToBeVisible(by);
		Actions move = new Actions(driver);

		Action action = (Action) move.dragAndDropBy(ele, 30, 0).build();
		action.perform();
	}

	public void slidebyArrow(By by) {
		WebElement ele = waitForElementToBeVisible(by);

		for (int i = 0; i < 10; i++) {
			ele.sendKeys(Keys.ARROW_RIGHT);
		}

	}

	protected String getText(By by) {
		return waitForElementToBeVisible(by).getText();
	}

	protected void waitForPageLoad() {
		((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	public WebElement waitForElementToBeVisible(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	protected String getTitle() {
		return driver.getTitle();
	}
}
