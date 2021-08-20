package mmt.drivermanager.browsertypes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import mmt.drivermanager.DriverManager;

// chromeDriverManager and getDriver() implementation with Chrome driver.

public class ChromeDriverManager  implements DriverManager{

		
	public WebDriver getDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}

	
	
	}










	
	

