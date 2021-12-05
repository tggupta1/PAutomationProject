package weatherShopperPages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import weatherShopperUtil.BaseSetup;
import weatherShopperUtil.Log;
import weatherShopperUtil.Utility;

public class ProductsPage extends BaseSetup {

	WebDriver driver;

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

	public ProductsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public String userProduct(WebDriver driver, String name) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		String productName = "//div[@class='text-center col-4']/p[1]";
		String productPrice = "//div[@class='text-center col-4']/p[2]";
		String itemName = null;
		try {
			List<WebElement> allProducts = driver.findElements(By.xpath(productName));
			Log.info("Total products displaying on Page are " + allProducts.size());
			for (int i = 0; i < allProducts.size(); i++) {
				System.out.println("Product name is " + allProducts.get(i).getText());
				if (allProducts.get(i).getText().toLowerCase().contains(name.toLowerCase())) {
					String pPrice = driver.findElements(By.xpath(productPrice)).get(i).getText();
					String[] pPriceArray = pPrice.split(" ");
					int pPriceValue = 0;
					if (pPriceArray.length == 2) {
						pPriceValue = Integer.valueOf(pPriceArray[1]);
					} else if (pPriceArray.length == 3) {
						pPriceValue = Integer.valueOf(pPriceArray[2]);
					}
					hm.put(allProducts.get(i).getText(), pPriceValue);
				}
			}
			HashMap<String, Integer> sorteMap = new HashMap<String, Integer>();
			sorteMap = Utility.sortByValue(hm);
			Log.info("Sorted Products are " + sorteMap);
			int count = 1;
			for (Map.Entry<String, Integer> en : sorteMap.entrySet()) {
				if (count < 2) {
					for (int i = 0; i < allProducts.size(); i++) {
						if (allProducts.get(i).getText().contains(en.getKey())) {
							driver.findElement(By.xpath("//p[text()='" + en.getKey() + "']/following-sibling::button"))
									.click();
							Log.info("Product - " + allProducts.get(i).getText() + " added in Cart successfully");
							itemName = allProducts.get(i).getText();
						}
					}
					count++;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return itemName;
	}
}
