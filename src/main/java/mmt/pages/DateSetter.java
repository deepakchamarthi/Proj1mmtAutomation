package mmt.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import mmt.drivermanager.DriverFactory;

public class DateSetter extends BasePage {
	
	
	BasePage base = new BasePage(20);
	
	
	public By datepicker =  By.xpath("//div[@class='DayPicker-Caption']/div");
	public By checkIn_button = By.id("checkin"); 
	public By nextmonth_Arrow = By.cssSelector("[aria-label*='Next Month']");

	
	
	
	public static void main(String[] args) {
		
		DriverFactory.initiateDriver();
		
		DateSetter ds = new DateSetter();
		//PageURL url = new PageURL();
		ds.openPage("https://www.makemytrip.com/hotels/");
		
		
		
		ds.getDate("27","August","2021");
	}

	
	public void getDate(String date, String MM, String YYYY)
	{
		base.waitForPageLoad();
		
		String month_Locator= MM.substring(0,3);
		
		WebElement checkin = driver.findElement(checkIn_button);
		Actions actions = new Actions(driver);
		

		actions.moveToElement(checkin).click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.moveToElement(checkin).click().perform();
		base.waitForElementToBeVisible(datepicker);
		
		List<WebElement> months = driver.findElements(datepicker);
		String MMYYYY = MM+YYYY;
		
		
		
		
		for (WebElement month : months)
		{
			
			
			if(month.getText().equals(MMYYYY))
			{
				base.waitForElementToBeVisible(By.cssSelector("[class=DayPicker-Day][aria-label*='" + YYYY
						+ "'][aria-label*='" + month_Locator + "'][aria-label*=' " + date + "']"));
				WebElement datePick = driver.findElement(By.cssSelector("[class=DayPicker-Day][aria-label*='" + YYYY
						+ "'][aria-label*='" + month_Locator + "'][aria-label*=' " + date + "']"));
				
				
				
				datePick.click();
				System.out.println("Clicked on "+month_Locator+date);
				break;
			}
					
			
			
			
			else
			{
				
				base.click(nextmonth_Arrow);
				System.out.println("Clicked on Next month");
				
				getDate(date,MM,YYYY);
				break;
				
			}
		}
		
		
	}
	
}
