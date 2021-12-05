package weatherShopperTest;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import weatherShopperPages.CheckOutPage;
import weatherShopperPages.ConfirmationPage;
import weatherShopperPages.PaymentPage;
import weatherShopperPages.WeatherShopperHomePage;
import weatherShopperUtil.BaseSetup;

public class AssignmentTC2 extends BaseTest {

	private static String PAGE_URL = "https://weathershopper.pythonanywhere.com/";
	WebDriver driver;
	BaseSetup bs;
	WeatherShopperHomePage wsHp;
	CheckOutPage checkOutPage;
	PaymentPage paymentPage;
	ConfirmationPage confimationPage;

	@Test
	@Parameters("Browser")
	public void verifyWeatherShopperEndToEnd_T2(String browser) throws InterruptedException {
		try {
			bs = new BaseSetup();
			ExtentTest test = report.startTest("verifyWeatherShopperEndToEnd_T2");
			driver = bs.setup(PAGE_URL, browser);
			test.log(LogStatus.PASS, "URL opened Successfully " + "on " + browser);
			wsHp = new WeatherShopperHomePage(driver, test);
			HashMap<String, Integer> products = wsHp.selectProductBaseOnTemperature();
			if (products != null) {
				test.log(LogStatus.PASS,
						"Product selected and added into cart based on Current Temperature Successfully");
			} else {
				test.log(LogStatus.FAIL, "Product selected and added into cart based on Current Temperature Failed");
				return;
			}
			checkOutPage = new CheckOutPage(driver, test);
			boolean isSuccessful = checkOutPage.verifyCartItemsandProceed(driver, products);
			if (isSuccessful) {
				test.log(LogStatus.PASS, "Items added in Cart, verified Successfully");
			} else {
				test.log(LogStatus.FAIL, "Items added in Cart, verification Failed");
				return;
			}
			paymentPage = new PaymentPage(driver, test);
			isSuccessful = paymentPage.enterPaymentDetails(driver);
			if (isSuccessful) {
				test.log(LogStatus.PASS, "Credit Card details updated and Payment button clicked Successfully");
			} else {
				test.log(LogStatus.FAIL, "Credit Card details updated and Payment button clicked Failed");
			}
			confimationPage = new ConfirmationPage(driver, test);
			isSuccessful = confimationPage.verifyOrderStatus(driver);
			if (isSuccessful) {
				test.log(LogStatus.PASS, "Payment completed and order placed Sucessfully");
			} else {
				test.log(LogStatus.FAIL, "Payment completed and order placement Failed");
			}
			report.endTest(test);
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			driver.close();
		}
	}
}
