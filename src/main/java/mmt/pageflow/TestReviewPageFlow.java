package mmt.pageflow;

import mmt.bo.FillGuestDetailsBO;
import mmt.pages.ReviewPage;

public class TestReviewPageFlow {

 /*	public static void main(String[] args) {

		DriverFactory.initiateDriver();
		ReviewPage rp = new ReviewPage();
		PageURL url = new PageURL();
		rp.openPage(url.reviewPageURL);

		// run_ReviewPage();
	} */
	

	//Runner to create flow in the Review Page
	public void run_ReviewPage() {

		ReviewPage rp = new ReviewPage();

		
		FillGuestDetailsBO guestsInfo = new FillGuestDetailsBO();
		//Fills up Fake guest details using Faker
		rp.fillDetails(guestsInfo);
		//Clicks on Pay button
		rp.clickPay();

	}

}
