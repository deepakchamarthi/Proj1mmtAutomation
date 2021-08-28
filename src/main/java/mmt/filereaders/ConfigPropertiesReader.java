package mmt.filereaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mmt.pages.HomePage;

public class ConfigPropertiesReader {

	static Logger logger = LogManager.getLogger(HomePage.class);

	// FilePaths
	public static String configFilePath = "C:\\Users\\Priyanka\\eclipse-workspace\\proj1mmt\\src\\main\\resources\\config.properties";
	public static Properties properties = new Properties();
	public static String integration_envFilePath = "C:\\Users\\Priyanka\\eclipse-workspace\\proj1mmt\\src\\main\\resources\\environment\\integration.properties";
	public static String staging_envFilePath = "C:\\Users\\Priyanka\\eclipse-workspace\\proj1mmt\\src\\main\\resources\\environment\\integration.properties";

	/**
	 * to read the properties of the env and main config files.
	 */
	public ConfigPropertiesReader() {
		getValueFromConfigFile(configFilePath, "browser");
		getValueFromConfigFile(integration_envFilePath, "baseUrl");

		// String environmentToRun = getProperty("environment");
		// getValueFromConfigFile(String.format(envFilePath, environmentToRun));

	}

	// for testing. Ignore
	public static void main(String args[]) {

		getValueFromConfigFile(configFilePath, "browser");
		getValueFromConfigFile(integration_envFilePath, "baseUrl");

	}

	/**
	 * 
	 * @param filePath = path of the configfile
	 * @param key      = key from the provided configfile to retrive value.
	 * @return in successful case it returns the value of the key in the config
	 *         file.
	 */

	public static String getValueFromConfigFile(String filePath, String key) {

		Properties properties = new Properties();

		try {
			FileInputStream fis = new FileInputStream(filePath);
			properties.load(fis);

			logger.info(key + "'s value is :" + properties.getProperty(key));
			return properties.getProperty(key);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("File Not Found " + key);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("IO Exception " + key);
			return null;
		} catch (Exception e) {

			e.printStackTrace();
			logger.info("Unable to find the Value of " + key);
			return null;

		}

	}

	public static String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

}
