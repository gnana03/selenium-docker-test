package com.automation.pilot.utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.asserts.SoftAssert;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.pilot.listeners.ExtentReportGenerator;

public class TestBaseClass extends ExtentReportGenerator {

	protected Applib applib;
	protected static SoftAssert Assertion;

	protected static String currentDir = System.getProperty("user.dir");

	protected static Logger logger = Logger.getLogger("devpinoyLogger");

	String url = TestDataRead.getDataFromTestDataProperty("URL");
	String userName = TestDataRead.getDataFromTestDataProperty("USER_NAME");
	String password = TestDataRead.getDataFromTestDataProperty("PASS_WORD");
	String browserName = TestDataRead.getDataFromTestDataProperty("BROWSER");
	boolean headless = Boolean.valueOf(TestDataRead.getDataFromTestDataProperty("HEADLESS"));

	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
	protected RemoteWebDriver driver = null;

	@BeforeSuite
	public void beforesuite() {
		System.out.println("Entered into a before suite");
		logger.debug("Entered into a before suite");
	}

	@AfterSuite
	public void aftersuite() {
		System.out.println("Entered into a after suite");
		logger.debug("Entered into a after suite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Entered into before test");
		logger.debug("Entered into before test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Entered into after test");
		logger.debug("Entered into after test");
	}

	@BeforeMethod
	public void beforeClass() {
		System.out.println("Before class");
		logger.debug("Entered into before Method");
	}

	@Parameters("browserName")
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void beforeMethod() throws InterruptedException, MalformedURLException {
	//public void beforeClass(@Optional("firefox") String browserName) throws InterruptedException {
		logger.debug("Entered into before method");
		System.out.println("Entered into before class");
		String host = "localhost";
        DesiredCapabilities dc;

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }else{
            dc = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        //String testName = ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";
//        dc.setCapability("name", testName);
        driver = new RemoteWebDriver(new URL(completeUrl), dc);
		setWebDriver(driver);
//		driver.manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		applib = PageFactory.initElements(getDriver(), Applib.class);
//		getDriver().get(url);
//		loginApp.login(userName, password);
		logger.debug("Login is sucessful");

	}
	
	public static WebDriver getDriver() {
		return dr.get();
	}

	public void setWebDriver(RemoteWebDriver driver) {
		dr.set(driver);
	}

	@Parameters("browserName")
	@AfterMethod
	public void afterMethod(ITestResult result, @Optional("firefox") String browserName) {

		File f = new File(currentDir + "//Screenshots");
		f.mkdir();
		String screenshotsPath = currentDir + "//Screenshots//" + browserName + "//" +result.getName() + ".png";
		System.out.println("After method");

		if (ITestResult.FAILURE == result.getStatus()) {
			System.out.println("Test failed");
			Screenshots.getScreenshotsAs(screenshotsPath);
			logger.debug(result.getName() + " is Failed");
		}

		else if (ITestResult.SKIP == result.getStatus()) {
			Screenshots.getScreenshotsAs(screenshotsPath);
			logger.debug(result.getName() + " is Skipped");
		}

		else {
			System.out.println(result.getName() + " Passed sucessfully");
			logger.debug(result.getName() + " Passed sucessfully");
		}
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		logger.debug("After Class");
//		driver.quit();
	}
}
