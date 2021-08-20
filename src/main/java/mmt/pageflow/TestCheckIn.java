package mmt.pageflow;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
//Trying to have configurable checkin checkout dates
//Still not part of main flow
public class TestCheckIn {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.makemytrip.com/hotels/");
		driver.manage().window().maximize();
		System.out.println("opened");
		Thread.sleep(3000);
		System.out.println("after sleep");

		WebDriverWait wait = new WebDriverWait(driver, 20, 200);

		// WebElement checkinButton =
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//label[@for='checkin']//span[2]"))));

	}

	public static void clickOnChkInChekOut(WebDriver driver) throws InterruptedException {

		WebElement checkin = driver.findElement(By.id("checkin"));

		Actions actions = new Actions(driver);

		actions.moveToElement(checkin).click().perform();
		Thread.sleep(2000);
		actions.moveToElement(checkin).click().perform();
		System.out.println("clicked");
		Thread.sleep(2000);

		clickondate(driver, "2021", "Aug", "25");
		clickondate(driver, "2021", "Aug", "27");

	}

	/*
	 * working code1 WebElement city = driver.findElement(By.id("city"));
	 * city.sendKeys(Keys.TAB);
	 * 
	 * System.out.println("");
	 */

	public static void clickondate(WebDriver driver, String year, String Month, String date) {

		WebElement datePick = driver.findElement(By.cssSelector("[class=DayPicker-Day][aria-label*='" + year
				+ "'][aria-label*='" + Month + "'][aria-label*=' " + date + "']"));
		datePick.click();
		System.out.println("Clicked on " + year + Month + date);

	}

}
