package mmt.pageflow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mmt.drivermanager.DriverFactory;
import mmt.pages.RoomSelectionPage;
import mmt.pages.SearchPage;

//For Testing puropse Not in Use
public class TestRoomSelectionFlow {
	
	
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
				
		DriverFactory.initiateDriver();
		RoomSelectionPage rs = new RoomSelectionPage();
		rs.openPage("https://www.makemytrip.com/hotels/hotel-details/?hotelId=200701131119237397&_uCurrency=INR&checkin=08252021&checkout=08312021&city=CTBOM&country=IN&filterData=HOTEL_PRICE%7C3000-20000%5ESTAR_RATING%7C4&lat=19.10922&lng=72.82554&locusId=CTBOM&locusType=city&rank=5&regionNearByExp=3&roomStayQualifier=4e3e4e3e5e&searchText=Mumbai%2C%20Maharashtra%2C%20India&viewType=PREMIUM&visitorId=c036d02f-3795-430d-bae9-ebfa14103927&mtkeys=defaultMtkey");
		
		run_RoomSelectionPage();
			}
	
	public static void run_RoomSelectionPage() throws InterruptedException
	{
			
		RoomSelectionPage rs = new RoomSelectionPage();
					
		rs.clickRooms();
		rs.bookCombo();
		//rs.click2audlts1ChildButton();
		//rs.getValue();
		//rs.VerifyText();
		
		}


	
}
