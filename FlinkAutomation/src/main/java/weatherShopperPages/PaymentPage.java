package weatherShopperPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import weatherShopperUtil.BaseSetup;
import weatherShopperUtil.Log;
import weatherShopperUtil.Utility;

public class PaymentPage extends BaseSetup {

	WebDriver driver;

	@FindBy(xpath = "//input[@id='email']")
	WebElement emailTB;

	@FindBy(xpath = "//input[@id='card_number']")
	WebElement cardNumberTB;

	@FindBy(xpath = "//input[@id='cc-exp']")
	WebElement ccExpTB;

	@FindBy(xpath = "//input[@id='cc-csc']")
	WebElement ccCvcTB;

	@FindBy(xpath = "//input[@placeholder='ZIP Code']")
	WebElement zipCodeTB;

	@FindBy(xpath = "//button[@id='submitButton']")
	WebElement payWithBN;

	Utility util = new Utility();
	ExtentTest test;

	public PaymentPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public boolean enterPaymentDetails(WebDriver driver) {
		try {
			driver.switchTo().frame("stripe_checkout_app");
			util.waitForEle(driver, emailTB);
			emailTB.sendKeys("test1@gmai.com");
			Thread.sleep(5000);
			cardNumberTB.sendKeys("4242 ");
			cardNumberTB.sendKeys("4242 ");
			cardNumberTB.sendKeys("4242 ");
			cardNumberTB.sendKeys("4242 ");
			ccExpTB.sendKeys("12");
			ccExpTB.sendKeys("22");
			ccCvcTB.sendKeys("121");
			Thread.sleep(500);
			util.waitForEle(driver, zipCodeTB);
			zipCodeTB.sendKeys("50001");
			Log.info("User Credit Card information updated successfully");
			payWithBN.click();
			driver.switchTo().defaultContent();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return false;
		}
	}
}
