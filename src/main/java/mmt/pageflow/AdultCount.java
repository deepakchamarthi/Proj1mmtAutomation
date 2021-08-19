package mmt.pageflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AdultCount {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.makemytrip.com/hotels/");
		driver.manage().window().maximize();
		System.out.println("opened");
		Thread.sleep(3000);
		System.out.println("after sleep");
		
		
		TestCheckIn.clickOnChkInChekOut(driver);
		
		clickOnguests(driver);
		Thread.sleep(2000);
		SelectGuestCount(driver,4,3);
		selectApply(driver);
		travelReson(driver);
		submit(driver);
		
		

	}
	
	public static void clickOnguests(WebDriver driver) throws InterruptedException
	{
		
WebElement guest = driver.findElement(By.id("guest"));
		
		Actions actions = new Actions(driver);

		actions.moveToElement(guest).click().perform();
		Thread.sleep(3000);
		actions.moveToElement(guest).click().perform();
		System.out.println("clicked on Guest");
		
		
	}
	
	public static void SelectGuestCount(WebDriver driver,int adults, int children) throws InterruptedException
	{
		
				
		WebElement adultsCount = driver.findElement(By.cssSelector("[data-cy=adults-"+adults+"]"));
		adultsCount.click();
		System.out.println("clicked on Adults");
		Thread.sleep(2000);
		
		
		WebElement ChildrenCount = driver.findElement(By.cssSelector("[data-cy=children-"+children+"]"));
		ChildrenCount.click();
		System.out.println("clicked children");
		Thread.sleep(2000);
		
		
		for(int i=0;i<children;i++)
		{
			WebElement selectAge = driver.findElement(By.cssSelector("[data-cy=childAge-"+i+"]"));
			selectAge.click();
			Thread.sleep(2000);
			Select select = new Select(selectAge);
			select.selectByVisibleText("3");
			//WebElement selectChildAge = driver.findElement(By.cssSelector("[data-cy='childAgeValue-3']"));
			//selectChildAge.click();
			System.out.println("Selected Children Age"+i);
			Thread.sleep(2000);
		}
	
	}
	
	
	public static void travelReson(WebDriver driver) throws InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver, 20, 200);

		//WebElement checkinButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//label[@for='checkin']//span[2]"))));
		
		WebElement travel = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[data-cy*=travelForText]"))));
				
				
		travel.click();
		System.out.println("Clicked on TravelFor");
		Thread.sleep(2000);
		
		WebElement travelReason = driver.findElement(By.cssSelector("[data-cy='travelFor-Leisure']"));
		System.out.println("Clicked on Leisure");
		travelReason.click();
		Thread.sleep(2000);
			
		
	}
	
	public static void submit(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement submit = driver.findElement(By.cssSelector("[data-cy='submit']"));
		System.out.println("Clicked on submit");
		submit.click();
		
		
		
		
	}
	
	public static void selectApply(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement apply = driver.findElement(By.cssSelector("[data-cy='submitGuest']"));
		apply.click();
		System.out.println("Clicked on Apply");
	}
	
	
}
