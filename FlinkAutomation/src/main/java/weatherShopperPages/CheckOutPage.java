package weatherShopperPages;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import weatherShopperUtil.BaseSetup;
import weatherShopperUtil.Log;
import weatherShopperUtil.Utility;

public class CheckOutPage extends BaseSetup {

	WebDriver driver;

	@FindBy(xpath = "//button[@onclick='goToCart()']")
	WebElement gotoCartBN;

	@FindBy(xpath = "//span[text()='Pay with Card']")
	WebElement payWithCardBN;

	Utility util = new Utility();
	ExtentTest test;

	public CheckOutPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyCartItemsandProceed(WebDriver driver, HashMap<String, Integer> sorteMap) {
		try {
			System.out.println("Inside method verifyCartItems");
			gotoCartBN.click();
			int result = 0;
			for (int i = 1; i <= sorteMap.size(); i++) {
				if (sorteMap.containsKey(driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText())) {
					result = 1;
				} else {
					Log.info("Items added in Cart dont match with the Items in Cart");
					result = 0;
					return false;
				}
			}
			Log.info("Items added in Cart match with the Items in Cart");
			payWithCardBN.click();
			if (result == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return false;
		}
	}
}
