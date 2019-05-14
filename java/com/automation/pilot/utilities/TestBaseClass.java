package com.automation.pilot.utilities;

import java.io.File;
import org.testng.asserts.SoftAssert;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
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
import com.automation.pilot.pages.Acendify_PeoplesTab;
import com.automation.pilot.pages.Ascendfy_Requisitions;
import com.automation.pilot.pages.Ascendify_Login;
import com.automation.pilot.pages.Ascendify_Pipelines;

public class TestBaseClass extends ExtentReportGenerator {

	protected Acendify_PeoplesTab peoplesTab;
	protected Ascendify_Login loginApp;
	protected Ascendfy_Requisitions asc_requisitions;
	protected Applib applib;
	protected Ascendify_Pipelines pipelines;
	protected static SoftAssert Assertion;

	protected static String currentDir = System.getProperty("user.dir");

	protected static Logger logger = Logger.getLogger("devpinoyLogger");

	String url = TestDataRead.getDataFromTestDataProperty("URL");
	String userName = TestDataRead.getDataFromTestDataProperty("USER_NAME");
	String password = TestDataRead.getDataFromTestDataProperty("PASS_WORD");
	String browserName = TestDataRead.getDataFromTestDataProperty("BROWSER");
	boolean headless = Boolean.valueOf(TestDataRead.getDataFromTestDataProperty("HEADLESS"));

	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
	RemoteWebDriver driver = null;

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
	public void beforeMethod() {
		System.out.println("Before Method");
		logger.debug("Entered into before Method");
	}

	@Parameters("browserName")
	@SuppressWarnings("deprecation")
	@BeforeClass
//	public void beforeClass() throws InterruptedException {
	public void beforeClass(@Optional("firefox") String browserName) throws InterruptedException {
		logger.debug("Entered into before Class");
		System.out.println("Entered into before class");
		if (browserName.equalsIgnoreCase("chrome")) {
			if(System.getProperty("os.name").contains("Linux")) {
				System.setProperty("webdriver.chrome.driver", currentDir + "/browserdrivers/Linux/chromedriver");
			}
			else if(System.getProperty("os.name").contains("Mac"))
			{
				System.setProperty("webdriver.chrome.driver", currentDir + "/browserdrivers/Mac/chromedriver");
			}
			else {
				System.setProperty("webdriver.chrome.driver", currentDir + "/browserdrivers/Windows/chromedriver.exe");
			}
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
//			options.addArguments("--incognito");
			if(headless) {
			 options.addArguments("--window-size=1280,1696");
			 // Uncomment below line to enable headless mode
			 options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);
			logger.debug("Starting the chrome driver");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			if(System.getProperty("os.name").contains("Linux")) {
				System.setProperty("webdriver.gecko.driver", currentDir + "/browserdrivers/Linux/geckodriver");
			}
			else if(System.getProperty("os.name").contains("Mac"))
			{
				System.setProperty("webdriver.gecko.driver", currentDir + "/browserdrivers/Mac/geckodriver");
			}
			else {
				System.setProperty("webdriver.gecko.driver", currentDir + "/browserdrivers/Windows/geckodriver.exe");
			}
			 FirefoxOptions firefoxOptions = new FirefoxOptions();
			    
			if(headless) {
			    FirefoxBinary firefoxBinary = new FirefoxBinary();
			    DesiredCapabilities desiredCap = DesiredCapabilities.firefox();
			    desiredCap.setCapability("headless", true);
//			    firefoxBinary.addCommandLineOptions("--headless");
//			    firefoxOptions.setBinary(firefoxBinary);
				 // Uncomment below line to enable headless mode
				}
			driver = new FirefoxDriver(firefoxOptions);
			 driver.manage().window().maximize();
		} 
		else if (browserName.equalsIgnoreCase("safari")) {
			if(System.getProperty("os.name").contains("Linux")) {
//				System.setProperty("webdriver.safari.driver", currentDir + "/browserdrivers/Linux/geckodriver");
				logger.debug("Safari doesnot support for linux so quitting the test");
			}
			else if(System.getProperty("os.name").contains("Mac"))
			{
				logger.debug("Safari doesnot support for Mac so quitting the test");
			}
			else {
				System.setProperty("webdriver.safari.driver", currentDir + "/browserdrivers/Windows/geckodriver.exe");
			}
			 FirefoxOptions firefoxOptions = new FirefoxOptions();
			    
			if(headless) {
			    FirefoxBinary firefoxBinary = new FirefoxBinary();
			    firefoxBinary.addCommandLineOptions("--headless");
			    firefoxOptions.setBinary(firefoxBinary);
				 // Uncomment below line to enable headless mode
				}
			driver = new SafariDriver(firefoxOptions);
			 driver.manage().window().maximize();
		} 
		else if (browserName.equalsIgnoreCase("edge")) {
			if(System.getProperty("os.name").contains("Linux")) {
//				System.setProperty("webdriver.safari.driver", currentDir + "/browserdrivers/Linux/geckodriver");
				logger.debug("Safari doesnot support for linux so quitting the test");
			}
			else if(System.getProperty("os.name").contains("Mac"))
			{
				logger.debug("Safari doesnot support for Mac so quitting the test");
			}
			else {
				System.setProperty("webdriver.safari.driver", currentDir + "/browserdrivers/Windows/MicrosoftWebDriver.exe");
			}
			 FirefoxOptions firefoxOptions = new FirefoxOptions();
			    
			if(headless) {
			    FirefoxBinary firefoxBinary = new FirefoxBinary();
			    firefoxBinary.addCommandLineOptions("--headless");
			    firefoxOptions.setBinary(firefoxBinary);
				 // Uncomment below line to enable headless mode
				}
			driver = new FirefoxDriver(firefoxOptions);
			 driver.manage().window().maximize();
		} 
		else {
			driver = new InternetExplorerDriver();
		}
		Assertion = new SoftAssert();
		setWebDriver(driver);
//		driver.manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		peoplesTab = PageFactory.initElements(getDriver(), Acendify_PeoplesTab.class);
		loginApp = PageFactory.initElements(getDriver(), Ascendify_Login.class);
		asc_requisitions = PageFactory.initElements(getDriver(), Ascendfy_Requisitions.class);
		applib = PageFactory.initElements(getDriver(), Applib.class);
		pipelines = PageFactory.initElements(getDriver(), Ascendify_Pipelines.class);
		getDriver().get(url);
		loginApp.login(userName, password);
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
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
