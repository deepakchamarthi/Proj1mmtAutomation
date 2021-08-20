package testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import mmt.bo.PageURL;
import mmt.drivermanager.DriverFactory;
import mmt.pages.RoomSelectionPage;
import mmt.pages.RoomSelectionPageAssert;

public class CheckRoomAllocation {
	

	@BeforeMethod 
	public void driverInit()
	{
		DriverFactory.initiateDriver();
		RoomSelectionPage rs = new RoomSelectionPage();
		PageURL url = new PageURL();
		rs.openPage(url.roomPageURL);
		
		 
	}
	
	@Test
	public void checkAllocationDetails()
	{
		RoomSelectionPage rs = new RoomSelectionPage();
		RoomSelectionPageAssert roomAssert = new RoomSelectionPageAssert();
		

		//Clicks on "Rooms"
		rs.clickRooms();
		
		roomAssert.verifyGuestCountFromRooms();

		//Clicks the room combo button
		rs.bookCombo();
	}
	
	
	
	
	

}
