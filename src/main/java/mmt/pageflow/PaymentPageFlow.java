package mmt.pageflow;

import mmt.bo.PageURL;
import mmt.drivermanager.DriverFactory;
import mmt.pages.PaymentPage;

public class PaymentPageFlow {

	/*
	 * public static void main(String[] args) {
	 
		

		DriverFactory.initiateDriver();
		PaymentPage pp = new PaymentPage();
		PageURL url = new PageURL();
		pp.openPage(url.paymentPageURL);

		// driver.manage().window().maximize();
		// System.out.println("after sleep");
		// run_PaymentPage();
	} */

	
	//Runner to run the methods in PaymentPage
	public void run_PaymentPage() {

		PaymentPage pp = new PaymentPage();

		//Gets the checkin chekout details.
		pp.getDetails();

	}

}
