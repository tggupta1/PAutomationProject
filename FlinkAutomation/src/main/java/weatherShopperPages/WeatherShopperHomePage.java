package weatherShopperPages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import weatherShopperUtil.BaseSetup;
import weatherShopperUtil.Log;
import weatherShopperUtil.Utility;

public class WeatherShopperHomePage extends BaseSetup {

	WebDriver driver;

	@FindBy(xpath = "//span[@id='temperature']")
	WebElement currentTempearture;

	@FindBy(xpath = "//button[text()='Buy moisturizers']")
	WebElement buyMoisturizersBN;

	@FindBy(xpath = "//button[text()='Buy sunscreens']")
	WebElement buySunscreenBN;

	@FindBy(xpath = "//button[@onclick='goToCart()']")
	WebElement gotoCartBN;

	@FindBy(xpath = "//span[text()='Pay with Card']")
	WebElement payWithCardBN;

	@FindBy(xpath = "//input[@id='email']")
	WebElement emailTB;

	@FindBy(xpath = "//input[@id='card_number']")
	WebElement cardNumberTB;

	@FindBy(xpath = "//input[@id='cc-exp']")
	WebElement ccExpTB;

	@FindBy(xpath = "//input[@id='cc-csc']")
	WebElement ccCvcTB;

	@FindBy(xpath = "//input[@id='submitButton'")
	WebElement submitBN;

	Utility util = new Utility();
	ExtentTest test;
	ProductsPage productPage = new ProductsPage(driver, test);

	public WeatherShopperHomePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public HashMap<String, Integer> selectProductBaseOnTemperature() throws InterruptedException {
		try {
			String temperature = currentTempearture.getText();
			String[] tempArray = temperature.split(" ");
			String temp = tempArray[0].trim();
			int currentTemp = Integer.valueOf(temp);
			Log.info("Current Temperature is " + temp);
			if (currentTemp < 19) {
				HashMap<String, Integer> moisturizer = buyMoisturizers();
				return moisturizer;
			} else if (currentTemp > 34) {
				HashMap<String, Integer> sunscreen = buySunscreen();
				return sunscreen;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return null;
		}
	}

	public HashMap<String, Integer> buyMoisturizers() {
		buyMoisturizersBN.click();
		String aloeMoisturizer = productPage.userProduct(driver, "Aloe");
		String almondMoisturizer = productPage.userProduct(driver, "Almond");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put(aloeMoisturizer, 1);
		hm.put(almondMoisturizer, 1);
		return hm;
	}

	public HashMap<String, Integer> buySunscreen() {
		buySunscreenBN.click();
		String spf50Sunscreen = productPage.userProduct(driver, "SPF-50");
		String spf30Sunscreen = productPage.userProduct(driver, "SPF-30");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put(spf50Sunscreen, 1);
		hm.put(spf30Sunscreen, 1);
		return hm;
	}

}