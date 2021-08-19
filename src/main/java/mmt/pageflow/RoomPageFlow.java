package mmt.pageflow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mmt.bo.PageURL;
import mmt.drivermanager.DriverFactory;
import mmt.pages.RoomSelectionPage;
import mmt.pages.SearchPage;

public class RoomPageFlow {
	

	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
				
		DriverFactory.initiateDriver();
		RoomSelectionPage rs = new RoomSelectionPage();
		PageURL url = new PageURL();
		rs.openPage(url.roomPageURL);
		
		//driver.manage().window().maximize();
		//System.out.println("after sleep");
		//run_RoomSelectionPage();
			}
	
	
	public void run_RoomSelectionPage() 
	{
			
		RoomSelectionPage rs = new RoomSelectionPage();
					
		rs.clickRooms();
		
		rs.bookCombo();
		
		//rs.click2audlts1ChildButton();
		//rs.getValue();
		//rs.VerifyText();
		
		}


	
}



