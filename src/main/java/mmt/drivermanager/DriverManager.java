package mmt.drivermanager;

import org.openqa.selenium.WebDriver;


//Interface to manage the driver. Browser Driver Manager has to implement the abstract method. getDriver()
public interface DriverManager {

	public WebDriver getDriver();
}
