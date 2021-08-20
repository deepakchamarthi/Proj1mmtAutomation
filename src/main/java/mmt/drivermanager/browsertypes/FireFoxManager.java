package mmt.drivermanager.browsertypes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import mmt.drivermanager.DriverManager;

//chromeDriverManager and getDriver() implementation with Firefox driver.
public class FireFoxManager implements DriverManager{

	@Override
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}
	
	
	

}
