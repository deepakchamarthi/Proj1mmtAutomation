package mmt.pageflow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import mmt.drivermanager.DriverFactory;
import mmt.drivermanager.browsertypes.ChromeDriverManager;

//This class in for Testing Purpose. CAN BE DELATED. IGNORE

public class HomePageFlow {

	public static Logger logger = LogManager.getLogger(HomePageFlow.class);

	public static void main(String[] args) throws InterruptedException {

		// DriverFactory df = new DriverFactory();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// to maximize browser window
		// driver.manage().window().maximize();

		// open web page

		driver.get("https://www.makemytrip.com/hotels/");
		System.out.println("opened");
		Thread.sleep(3000);
		System.out.println("after sleep");
		pickCheckInDate();

	}

	public static void pickCheckInDate() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("checkin")).click();
		Thread.sleep(2000);

		Date d = new Date(1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
		String date = formatter.format(d);
		String splitter[] = date.split("-");
		String month_year = splitter[1];
		String day = splitter[0];
		System.out.println(month_year);
		System.out.println(day);

		selectDate(month_year, day);
		Thread.sleep(3000);
	}

	public static void selectDate(String month_year, String select_day) throws InterruptedException

	{
		WebDriver driver = new ChromeDriver();
		List<WebElement> elements = driver.findElements(By.cssSelector("[class*='DayPicker Selectable']"));

		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).getText());

			// Selecting the month
			if (elements.get(i).getText().equals(month_year)) {

				// Selecting the date
				List<WebElement> days = driver
						.findElements(By.cssSelector("[class*='DayPicker-Month'][role='grid']:last-child"));

				for (WebElement d : days) {
					System.out.println(d.getText());
					if (d.getText().equals(select_day)) {
						d.click();
						Thread.sleep(10000);
						return;
					}
				}

			}

		}
		driver.findElement(By.cssSelector("[class*='DayPicker-Month'][role='grid']:last-child")).click();
		selectDate(month_year, select_day);

	}

	public void selectcity() throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		WebElement city = driver.findElement(By.id("city"));

		Thread.sleep(2000);
		city.sendKeys("Delhi");
		System.out.println("Sent delhi");

		Thread.sleep(2000);
		WebElement citymenu = driver.findElement(By.cssSelector("[placeholder*='city']"));
		Thread.sleep(2000);
		citymenu.sendKeys("Mumbai");
		System.out.println("Sent mumbai");

		citymenu.sendKeys(Keys.ARROW_DOWN);
		System.out.println("arrow down1");
		Thread.sleep(2000);
		citymenu.sendKeys(Keys.RETURN);
		System.out.println("Enter1");
		Thread.sleep(2000);
		// city.sendKeys(Keys.RETURN);

		System.out.println("end of program");

	}

}
