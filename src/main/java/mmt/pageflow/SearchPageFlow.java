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

public class SearchPageFlow {

	public static void main(String[] args) {

		DriverFactory.initiateDriver();
		SearchPage search = new SearchPage();
		PageURL url = new PageURL();
		search.openPage(url.searchPageURL);

		run_SearchPage();
	} 

	
	//Runner to run all the element interaction methods in SearchPage ( page2)
	public  static void run_SearchPage() {

		SearchPage search = new SearchPage();

		//Scroll the price selector control
		search.scrollControlBy();
		//search.scrollControl(5, 0, SearchPage.price_Selector);
		//Select the Rating. 
		search.startRating(StarRating.RATING4);
		//Select the 5th hotel in the list.
		//if the list has less than 5 hotels then closest the no#5 will be selected. ( the last hotel)
		search.selectHotel(SearchPage.hotel_List, 5);
		//Switches the control to a newTab
		search.switchToNewTab();

	}

}
