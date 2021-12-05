package weatherShopperUtil;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import weatherShopperUtil.Log;

public class BaseSetup {

	WebDriver driver;

	public WebDriver setup(String url, String browser) throws InterruptedException {
		DOMConfigurator.configure("log4j.xml");
		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", path + "\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Log.info("URL opened successfully");
		String pageTitle = "Current Temperature";
		Assert.assertEquals(pageTitle, driver.getTitle());
		return driver;
	}

}
