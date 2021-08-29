package mmt.drivermanager;

import org.openqa.selenium.WebDriver;

import mmt.drivermanager.browsertypes.ChromeDriverManager;
import mmt.drivermanager.browsertypes.FireFoxManager;
import mmt.filereaders.ConfigPropertiesReader;

//DriverFactory initiates the required driver
public class DriverFactory {

	public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();

	public static void main(String args[]) {

		initiateDriver();
	}

	public static void initiateDriver() {

		WebDriver driver = null;

		//Reading browser type form the config file and passing to swtich-case 
		String browserType = ConfigPropertiesReader.getValueFromConfigFile(ConfigPropertiesReader.configFilePath,
				"browser");

		// switch-case to initiate driver based on user selected browser type from
		// config file.
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
