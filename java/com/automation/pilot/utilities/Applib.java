package com.automation.pilot.utilities;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class Applib extends TestBaseClass {

	// locators

	@FindBy(how = How.CSS, using = "span[id*='add-']")
	static WebElement btn_newRequisition;

	@FindBy(how = How.CSS, using = "a[id*='manually']")
	static WebElement btn_addManually;

	static Actions action = new Actions(getDriver());

	/*
	 * To get WebElement by path
	 * 
	 * @param : byValue - String
	 * 
	 * @Param : path - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static By getWebElementByPath(String byValue, String path) {
		if (byValue.equalsIgnoreCase("css"))
			return By.cssSelector(path);
		else if (byValue.equalsIgnoreCase("xpath"))
			return By.xpath(path);
		else if (byValue.equalsIgnoreCase("id"))
			return By.id(path);
		else if (byValue.equalsIgnoreCase("name"))
			return By.name(path);
		else if (byValue.equalsIgnoreCase("class"))
			return By.className(path);
		else if (byValue.equalsIgnoreCase("link"))
			return By.linkText(path);
		else if (byValue.equalsIgnoreCase("partiallink"))
			return By.partialLinkText(path);
		else
			return null;

	}

	/*
	 * To get WebElement by Based On Text
	 * 
	 * @param : byValue - String
	 * 
	 * @Param : path - String
	 * 
	 * @param : substitutionValue - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static WebElement getWebElementBasedOnText(String byValue, String pathValue, String substitutionValue)
			throws InterruptedException {

		if (byValue.equalsIgnoreCase("xpath"))
			return WaitFunctions.waitTillElementIsVisible(
					getDriver().findElement(By.xpath(pathValue.replace("***", substitutionValue))));

		else if (byValue.equalsIgnoreCase("css"))
			return WaitFunctions.waitTillElementIsVisible(
					getDriver().findElement(By.cssSelector(pathValue.replace("***", substitutionValue))));

		else if (byValue.equalsIgnoreCase("id"))
			return getDriver().findElement(By.id(pathValue.replace("***", substitutionValue)));

		else if (byValue.equalsIgnoreCase("name"))
			return getDriver().findElement(By.name(pathValue.replace("***", substitutionValue)));

		else if (byValue.equalsIgnoreCase("class"))
			return getDriver().findElement(By.className(pathValue.replace("***", substitutionValue)));

		else if (byValue.equalsIgnoreCase("link"))
			return getDriver().findElement(By.linkText(pathValue.replace("***", substitutionValue)));

		else if (byValue.equalsIgnoreCase("partiallink"))
			return getDriver().findElement(By.partialLinkText(pathValue.replace("***", substitutionValue)));
		else
			return null;

	}

	/*
	 * To get WebElement by Based On Text
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @param : substitutionValue - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static WebElement getWebElementBasedOnText(WebElement ele, String substitutionValue)
			throws InterruptedException {
		// see RemoteWebElement toString() implementation
		String[] data = ele.toString().split("By.")[1].replaceAll("'", "").split(":");
		String locator = data[0];
		String term = data[1].replace("***", substitutionValue);

		switch (locator) {
		case "xpath":
			return WaitFunctions.waitForElementToBeClickable(getDriver().findElement(By.xpath(term)));
		case "css selector":
			return WaitFunctions.waitForElementToBeClickable(getDriver().findElement(By.cssSelector(term)));
		case "id":
			return getDriver().findElement(By.id(term));
		case "tag name":
			return getDriver().findElement(By.tagName(term));
		case "name":
			return getDriver().findElement(By.name(term));
		case "link text":
			return getDriver().findElement(By.linkText(term));
		case "class name":
			return getDriver().findElement(By.className(term));
		default:
			return null;
		}

	}

	/*
	 * To get WebElement based on text
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @Param : pathValue - String
	 * 
	 * @param : substitutionValue - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void clickAnElementBasedOnText(String byValue, String pathValue, String substitutionValue)
			throws InterruptedException {
		WebElement eleToClick = getWebElementBasedOnText(byValue, pathValue, substitutionValue);
		eleToClick.click();
	}

	/*
	 * To click WebElement based on text
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @param : substitutionValue - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void clickAnElementBasedOnText(WebElement ele, String substitutionValue) throws InterruptedException {
		WebElement eleToClick = getWebElementBasedOnText(ele, substitutionValue);
		eleToClick.click();
	}

	/*
	 * To select an option
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @param : option - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void selectAnOption(WebElement ele, String option) throws InterruptedException {
		WebElement mySelectElement = WaitFunctions.waitTillElementIsVisible(ele);
		// mySelectElement.click();
		Select dropdown = new Select(mySelectElement);
		dropdown.selectByVisibleText(option);
		action.sendKeys(Keys.ESCAPE).build().perform();
		;
		Thread.sleep(500);
	}

	/*
	 * To Scroll to an Element
	 * 
	 * @Param : element - Web Element
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void scrollToElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	/*
	 * To get WebElement by Based On Text
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @param : substitutionValue - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void scrollToElement1(WebElement element) throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(500);
	}

	/*
	 * To scroll to an element
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @param : offset - Integer
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void scrollToElement(WebElement element, int offset) throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollTop = arguments[1];", element, offset);
		Thread.sleep(500);
	}

	/*
	 * To refresh the page
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void refreshPage() {
		getDriver().navigate().refresh();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * To close current child window
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void closeCurrentChildWindow() {
		String parentWindow = getDriver().getWindowHandle();
		String childwindow = null;
		String windowToSwitch = null;
		WaitFunctions.waitForPageLoaded();
		Set<String> windows = getDriver().getWindowHandles();
		for (String window : windows) {
			if (window.equals(parentWindow))
				childwindow = window;
			else
				windowToSwitch = window;
		}
		getDriver().switchTo().window(childwindow);
		getDriver().close();
		getDriver().switchTo().window(windowToSwitch);
	}

	/*
	 * To change to By Val
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
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

	/*
	 * To mouse hover an element for some time
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @param : time - Integer
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void mouseHoverAnelementForTime(WebElement element, int time) {
		action.moveToElement(element).pause(Duration.ofSeconds(time)).build().perform();
	}

	/*
	 * To accept an Alert
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void acceptAlert() {
		getDriver().switchTo().alert().accept();
	}

	/*
	 * Move Down Till Element Is Found
	 * 
	 * @Param : ele - Web Element
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static boolean moveDownTillElementIsFound(WebElement ele) {
		boolean present = false;
		try {
			action.sendKeys(Keys.ARROW_DOWN);
			present = ele.isDisplayed();
			if (present) {
				return true;
			} else {
				return moveDownTillElementIsFound(ele);
			}
		} catch (Exception e) {
			return moveDownTillElementIsFound(ele);
		}
	}

	/*
	 * click Add Manually
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static void clickAddManually() throws InterruptedException {
		logger.debug("Adding manually");
		WaitFunctions.waitForElementToBeClickable(btn_newRequisition).click();
		WaitFunctions.waitForElementToBeClickable(btn_addManually).click();
		WaitFunctions.waitFor(1000);
	}

	/*
	 * Get the map from properties
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public static Map<String,String> createMapFromPropertyFile(String item) throws InterruptedException {
		logger.debug("Given item is "+item);
		Map<String,String> resultMap = new LinkedHashMap<String,String>();
		String[] allValues=item.split(",");
		for(String values:allValues)
		{
			String key = values.split(":")[0];
			String value = values.split(":")[1];
			resultMap.put(key, value);
		}
		return resultMap;
	}
}
