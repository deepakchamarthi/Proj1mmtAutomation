package mmt.pageflow;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.StarRating;
import io.github.bonigarcia.wdm.WebDriverManager;
import mmt.bo.PageURL;
import mmt.drivermanager.DriverFactory;
import mmt.pages.HomePage;
import mmt.pages.SearchPage;

public class SearchPageFlow1 {

	public static void main(String[] args) {

		DriverFactory.initiateDriver();
		SearchPage search = new SearchPage();
		PageURL url = new PageURL();
		search.openPage(url.searchPageURL);

		//run_SearchPage();
	}

	public  void run_SearchPage() {

		SearchPage search = new SearchPage();

		search.scrollControl(5, 0, SearchPage.price_Selector);
		search.startRating(StarRating.RATING4);
		search.selectHotel(SearchPage.hotel_List, 5);
		search.switchToNewTab();

	}

}
