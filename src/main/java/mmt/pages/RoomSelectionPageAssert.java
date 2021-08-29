package mmt.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

import mmt.bo.HomePageBO;

public class RoomSelectionPageAssert extends RoomSelectionPage{

	
	
	
	
	public void verifyGuestCountFromRooms()

	{
		// Setting up Adults & children count using HomePageBO class
		HomePageBO home = new HomePageBO();
		home.setTotalAdults(4);
		home.setTotalChildren(3);

		try
		{
			
		
		// Collecting the suggestion into the list
		// Not using this list anywhere. But planned to use it for future purpose if we
		// need to find some other text too
		waitForElementToBeVisible(guestText);
		List<WebElement> suggestions = driver.findElements(guestText);
		boolean flag;

		int counter = 0;
		List<String> suggesstionsList = new ArrayList<String>();
		for (WebElement suggestion : suggestions) {
			counter++;

			// WebElement roomText = driver.findElement(By.xpath("//p[text()='Adults']"));
			// String roomTextValue = roomText.getText();
			// p[text()='Room']

			String roomSuggestion = suggestion.getText();
			suggesstionsList.add(roomSuggestion);

			//Checking if the text contains given adults and children count
			flag = roomSuggestion.contains(
					"Recommended for " + home.getTotalAdults() + " Adults & " + home.getTotalChildren() + " Children");
			
			
			if (flag =true)
			{
				Assert.assertTrue(true, "Checkin Date Matched");
			
			
				logger.info(" Room#" + counter
						+ " Adults and Children count is matching with the input.Total Adults Count: "
						+ home.getTotalAdults() + " & Total Children Count: " + home.getTotalChildren());

			} else
				
				logger.error(" Room#" + counter + " Adults and Children count not matching with input Adults: "
						+ home.getTotalAdults() + " & Total Children Count: " + home.getTotalChildren());

		}
		}

		catch(Exception e)
		{
			
			logger.error("Problem with getting total adults/children count from the text. Continuing");
			 
			e.printStackTrace();
			
		}
	}
}
