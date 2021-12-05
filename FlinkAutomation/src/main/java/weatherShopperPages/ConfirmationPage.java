package weatherShopperPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import weatherShopperUtil.BaseSetup;
import weatherShopperUtil.Log;
import weatherShopperUtil.Utility;

public class ConfirmationPage extends BaseSetup {

	WebDriver driver;

	@FindBy(xpath = "//div[@class='row justify-content-center']/h2")
	WebElement paymentStatus;

	@FindBy(xpath = "//p[@class='text-justify']")
	WebElement paymentMessage;

	Utility util = new Utility();
	ExtentTest test;

	public ConfirmationPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyOrderStatus(WebDriver driver) {
		try {
			util.waitForEle(driver, paymentStatus);
			if (paymentMessage.getText().contains("successful")) {
				Log.info("Payment completed and Order placed Successful");
				return true;
			} else {
				Log.info("Payment is Failed");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return false;
		}
	}
}
