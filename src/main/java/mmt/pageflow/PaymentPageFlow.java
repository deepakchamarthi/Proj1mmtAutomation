package mmt.pageflow;



import mmt.bo.PageURL;
import mmt.drivermanager.DriverFactory;
import mmt.pages.PaymentPage;


public class PaymentPageFlow {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		DriverFactory.initiateDriver();
		PaymentPage pp = new PaymentPage();
		PageURL url = new PageURL();
		pp.openPage(url.paymentPageURL);

		// driver.manage().window().maximize();
		// System.out.println("after sleep");
		// run_PaymentPage();
	}

	public void run_PaymentPage()  {

		PaymentPage pp = new PaymentPage();

		pp.getDetails();

	}

}
