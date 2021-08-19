package mmt.pageflow;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchListTest1 {
	
	public static By hotel_List  =  By.cssSelector("[class*='listingRow ']");
	public static By price_Selector = By.cssSelector("[role='slider'][aria-valuemin='0']");
	public static By rating_Checkbox =By.xpath("//p[text()='4 Star']");
	
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		
		

		driver.get(
				"https://www.makemytrip.com/hotels/hotel-listing/?_uCurrency=INR&checkin=08252021&checkout=08312021&city=CTBOM&country=IN&filterData=HOTEL_PRICE%7C3000-20000&locusId=CTBOM&locusType=city&regionNearByExp=3&roomStayQualifier=4e3e4e3e5e&searchText=Mumbai%2C%20Maharashtra%2C%20India&visitorId=c036d02f-3795-430d-bae9-ebfa14103927");
		driver.manage().window().maximize();
		System.out.println("opened");
		Thread.sleep(2000);
		System.out.println("after sleep");

		scrollControl(driver, 3000, 10000,price_Selector);
		startRating(driver,price_Selector);
		//hotelSelect(driver);
		SearchListTest1 sl = new SearchListTest1();
		sl.selectHotel(driver,hotel_List);

	}

	
	public static void scrollControl(WebDriver driver, int min, int max,By by) throws InterruptedException {

		WebElement price_slider = driver.findElement(by);

		price_slider.click();

		System.out.println("clicked slider");
		Thread.sleep(2000);

		// WebElement min1 =
		// driver.findElement(By.cssSelector("[role='slider'][aria-valuemin='0']"));

		// WebElement max1 =
		// driver.findElement(By.cssSelector("[role='slider'][aria-valuemin='6']"));

		// Actions action = new Actions(driver);

		// action.dragAndDropBy(price_slider,20,20).perform();

		// action.moveToElement(price_slider).moveToElement(max1).build().perform();

		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(price_slider, 5, 0).build();
		action.perform();

		Thread.sleep(2000);

		System.out.println("moved slider");

	}

	public static void startRating(WebDriver driver, By by) {
		WebElement rating = driver.findElement(by);
		rating.click();
		System.out.println("clicked 4 star");

	}

	
	public static void hotelSelect(WebDriver driver, By by)
	{
		List<WebElement> searchList = driver.findElements(by);
		
		//List<WebElement> searchList =driver.wait.until(ExpectedConditions.visibilityOfElementsLocated("[class*='listingRow ']"));
		System.out.println(" reached hotel select block "); 
		
		int counter = 0;
		for (WebElement hotel : searchList) {
			counter++;
			if(counter ==1)
			{
				hotel.click();
				System.out.println(
						"clicked in Else-if block beacuse counter: " + counter + "Max Hotels: " );
				
			}
		}
			
	}
	public  void selectHotel(WebDriver driver, By by) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10, 200); 

		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		List<WebElement> searchList = driver.findElements(by);
		int counter = 0;
		int maxHotelsInList = getNoOfHotels(driver,by);

		for (WebElement hotel : searchList) {
			counter++;

			if (maxHotelsInList == 0) {
				System.out.println("No hotels found" + counter);
				selectHotel(driver,by);
			}

			else if (counter == maxHotelsInList) {
				hotel.click();

				System.out
						.println("clicked in if block beacuse counter: " + counter + "Max Hotels: " + maxHotelsInList);
			}

			else if (counter == 5) {

				hotel.click();
				System.out.println(
						"clicked in Else-if block beacuse counter: " + counter + "Max Hotels: " + maxHotelsInList);
				break;
			}

			else {

				continue;
			}

		}

	}

	public static int getNoOfHotels(WebDriver driver,By by) {
		
		
		
		int hotelCounter = 0;
		List<WebElement> searchList = driver.findElements(by);

		for (WebElement hotel : searchList) {
			hotelCounter++;
		}

		return hotelCounter;

	}

}
