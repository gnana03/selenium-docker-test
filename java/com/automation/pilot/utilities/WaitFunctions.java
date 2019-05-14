package com.automation.pilot.utilities;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitFunctions extends TestBaseClass {

	@FindBy(css = ".loading_win>img")
	private static WebElement loadingImage;

	WebDriverWait wait = new WebDriverWait(driver, 60);

	/*
	 * wait till element is loaded
	 * 
	 * @param : Locator - By
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void waitTillElementIsLoaded(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/*
	 * To wait for some time
	 * 
	 * @param : time - Integer
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void waitFor(int time) throws InterruptedException {
		Thread.sleep(time);
	}

	/*
	 * To wait for an element
	 * 
	 * @param : element - WebElement
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static WebElement waitforAnElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		WebElement Element = wait.until(ExpectedConditions.presenceOfElementLocated(toByVal(element)));
		return Element;
	}

	/*
	 * To wait for an element to be clickable
	 * 
	 * @param : element - WebElement
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static WebElement waitForElementToBeClickable(WebElement element) throws InterruptedException {
		waitForPageLoaded();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class);
		int tries = 0;
		final long startTime = System.currentTimeMillis();
		boolean found = false;
		WebElement we = null;
		while ((System.currentTimeMillis() - startTime) < 91000) {
			logger.debug("Searching for element. Try number " + (tries++));
			try {
				we = wait.until(ExpectedConditions.visibilityOf(element));
				we = wait.until(ExpectedConditions.elementToBeClickable(element));
				found = element.isEnabled();
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				logger.debug("Stale element: \n" + e.getMessage() + "\n");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			Thread.sleep(2000);
			logger.debug("Found element after waiting for " + totalTime + " milliseconds.");
		} else {
			logger.debug("Failed to find element after " + totalTime + " milliseconds.");
		}
		return element;
	}

	/*
	 * To wait for alert
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void waitForAlert() throws InterruptedException {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class);
		int tries = 0;
		final long startTime = System.currentTimeMillis();
		boolean found = false;
		while ((System.currentTimeMillis() - startTime) < 91000) {
			logger.debug("Searching for Alert. Try number " + (tries++));
			try {
				wait.until(ExpectedConditions.alertIsPresent());
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				logger.debug("Stale element: \n" + e.getMessage() + "\n");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			Thread.sleep(2000);
			logger.debug("Found Alert after waiting for " + totalTime + " milliseconds.");
		} else {
			logger.debug("Failed Alert to find element after " + totalTime + " milliseconds.");
		}

	}

	/*
	 * To wait till element is loaded
	 * 
	 * @param : locator - WebElement
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public WebElement isElementLoaded(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
		return element;
	}

	/*
	 * To wait till element is clickable
	 * 
	 * @param : locator - WebElement
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public WebElement isElementClickable(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	/*
	 * To wait till element to be visible
	 * 
	 * @param : selector - By
	 * 
	 * @param : timeToWaitInSeconds - Integer
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void waitForElementToBeVisible(By selector, int timeToWaitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}

	/*
	 * To wait till element to be visible
	 * 
	 * @param : ele - WebElement
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static WebElement waitTillElementIsVisible(WebElement ele) throws InterruptedException {
		waitForPageLoaded();
		logger.debug("Get element by locator: " + ele.toString());
		final long startTime = System.currentTimeMillis();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		int tries = 0;
		boolean found = false;
		WebElement we = null;
		while ((System.currentTimeMillis() - startTime) < 91000) {
			logger.debug("Searching for element. Try number " + (tries++));
			try {
				we = wait.until(ExpectedConditions.visibilityOf(ele));
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				logger.debug("Stale element: \n" + e.getMessage() + "\n");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			Thread.sleep(1000);
			logger.debug("Found element after waiting for " + totalTime + " milliseconds.");
		} else {
			logger.debug("Failed to find element after " + totalTime + " milliseconds.");
		}
		return we;
	}
	
	public WebElement fluientWaitforElement(WebElement element) {

	    FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
	        .pollingEvery(Duration.ofSeconds(5))
	        .ignoring(NoSuchElementException.class, TimeoutException.class).ignoring(StaleElementReferenceException.class);

	    for (int i = 0; i < 2; i++) {
	        try {
	            //fWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='reportmanager-wrapper']/div[1]/div[2]/ul/li/span[3]/i[@data-original--title='We are processing through trillions of data events, this insight may take more than 15 minutes to complete.']")));
	        fWait.until(ExpectedConditions.visibilityOf(element));
	        fWait.until(ExpectedConditions.elementToBeClickable(element));
	        } catch (Exception e) {

	        System.out.println("Element Not found trying again - " + element.toString().substring(70));
	        e.printStackTrace();

	        }
	    }

	    return element;

	    }

	/*
	 * To wait for the element and text
	 * 
	 * @param : ele - WebElement
	 * 
	 * @param : text - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static WebElement waitForElementAndText(final WebElement element, String text) throws InterruptedException {
		// logger.debug("Get element by locator: " + element.toString());
		final long startTime = System.currentTimeMillis();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		int tries = 0;
		boolean found = false;
		WebElement we = null;
		while ((System.currentTimeMillis() - startTime) < 91000) {
			logger.debug("Searching for element. Try number " + (tries++));
			try {

				we = wait.until(ExpectedConditions.visibilityOf(element));
				found = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				logger.debug("Stale element: \n" + e.getMessage() + "\n");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			Thread.sleep(1000);
			logger.debug("Found element after waiting for " + totalTime + " milliseconds.");
		} else {
			logger.debug("Failed to find element after " + totalTime + " milliseconds.");
		}
		return we;
	}

	/*
	 * To wait for the element to disappear
	 * 
	 * @param : by - By
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	final public static boolean waitForElementToDisappear(final By by) {
		try {
			getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(getDriver(), 60);

			boolean present = wait.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOfElementLocated(by));

			return present;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * To wait for page loaded
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/*
	 * To wait till loading image is Disappeared
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void waitTillLoadingImageIsDisappeared(WebElement ele) throws InterruptedException {
		int count = 0;
		final long startTime = System.currentTimeMillis();
		try {
			int wait = 1000;
			while (ele.isDisplayed() && count <= 90) {
				Thread.sleep(wait);
				count = count + 1;
			}
			logger.debug(System.currentTimeMillis() - startTime);
		} catch (Exception e) {
			logger.debug("Exception is thrown in waiting for the element to disappear " + e.getMessage());
			logger.debug(count);
			logger.debug(System.currentTimeMillis() - startTime);

		}
	}

	/*
	 * To return webelement to by value
	 * 
	 * @param : we - WebElement
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : By
	 */
	public static By toByVal(WebElement we) {
		// By format = "[foundFrom] -> locator: term"
		// see RemoteWebElement toString() implementation
		String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
		String locator = data[0];
		String term = data[1];

		switch (locator) {
		case "xpath":
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		return (By) we;
	}

}
