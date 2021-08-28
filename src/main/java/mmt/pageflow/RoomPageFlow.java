package mmt.pageflow;

import mmt.pages.RoomSelectionPage;

public class RoomPageFlow {

	/* public static void main(String[] args) {
		

		DriverFactory.initiateDriver();
		RoomSelectionPage rs = new RoomSelectionPage();
		PageURL url = new PageURL();
		//rs.openPage(url.roomPageURL);
		rs.openPage("https://www.makemytrip.com/hotels/hotel-details/?hotelId=202101061147093754&_uCurrency=INR&checkin=08252021&checkout=08312021&city=CTDEL&country=IN&filterData=STAR_RATING%7C4&lat=28.52617&lng=77.258&locusId=CTDEL&locusType=city&rank=5&regionNearByExp=3&roomStayQualifier=4e3e2e9e5e&searchText=Delhi&viewType=BUDGET&visitorId=dce8cc4d-95b8-4196-8605-78957f3e730a&mtkeys=defaultMtkey");
		 run_RoomSelectionPage();

	} */

	
	//Runner to run different methods in RoomSelectionPage
	public  void run_RoomSelectionPage() {

		RoomSelectionPage rs = new RoomSelectionPage();

		//Clicks on "Rooms"
		rs.clickRooms();

		//Clicks the room combo button
		rs.bookCombo();

	}

}
