package mmt.drivermanager;

import org.openqa.selenium.WebDriver;

import mmt.drivermanager.browsertypes.ChromeDriverManager;
import mmt.drivermanager.browsertypes.FireFoxManager;
import mmt.filereaders.ConfigPropertiesReader;
import mmt.filereaders.ConfigReader;



public class DriverFactory {

	public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();
	
	public static void main(String args[])
	{
		
		initiateDriver();
	}
	
	

	public static void initiateDriver() {

		WebDriver driver = null;
		
		ConfigPropertiesReader config = new ConfigPropertiesReader();
		
		String browserType = ConfigPropertiesReader.getValueFromConfigFile(ConfigPropertiesReader.configFilePath,"browser");
		//String brows = ConfigPropertiesReader.getValueFromConfigFile(ConfigPropertiesReader.integration_envFilePath,"browser");
		
		
		//String browserType="chrome";
		
		System.out.println(browserType);
		
		

		switch (BrowserType.valueOf(browserType.toUpperCase())) {
		case CHROME:
			driver = new ChromeDriverManager().getDriver();
			break;
		case FIREFOX:
			driver = new FireFoxManager().getDriver();
			break;
		default:
			
			throw new IllegalStateException("UnSupported Browser Type provided");
		}

		driverThreadLocal.set(driver);
	}

	public static WebDriver getCurrentDriver() {
		return driverThreadLocal.get();
	}
}
