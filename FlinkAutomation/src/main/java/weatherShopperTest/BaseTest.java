package weatherShopperTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;

public class BaseTest {

	static ExtentReports report;

	@BeforeSuite
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
	}

	@AfterSuite
	public static void endTest() {
		report.flush();
	}

}
