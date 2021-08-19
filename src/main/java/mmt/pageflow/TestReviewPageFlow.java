package mmt.pageflow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mmt.bo.FillGuestDetailsBO;
import mmt.bo.PageURL;
import mmt.drivermanager.DriverFactory;
import mmt.pages.ReviewPage;
import mmt.pages.RoomSelectionPage;

public class TestReviewPageFlow {

	public static void main(String[] args) {

		DriverFactory.initiateDriver();
		ReviewPage rp = new ReviewPage();
		PageURL url = new PageURL();
		rp.openPage(url.reviewPageURL);

		// run_ReviewPage();
	}

	public void run_ReviewPage() {

		ReviewPage rp = new ReviewPage();

		FillGuestDetailsBO guestsInfo = new FillGuestDetailsBO();

		rp.fillDetails(guestsInfo);
		rp.clickPay();

	}

}
