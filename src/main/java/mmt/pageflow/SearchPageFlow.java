package mmt.pageflow;

import mmt.constants.StarRating;
import mmt.pages.SearchPage;

public class SearchPageFlow {

		
	//Runner to run all the element interaction methods in SearchPage ( page2)
	public   void run_SearchPage() {

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
