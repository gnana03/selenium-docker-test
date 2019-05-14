package com.automation.pilot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.pilot.utilities.TestBaseClass;
import com.automation.pilot.utilities.WaitFunctions;

public class Ascendify_Login extends TestBaseClass {

	@FindBy(how = How.CSS, using = "input#id_username")
	WebElement userNameEle;

	@FindBy(how = How.CSS, using = "input#id_password")
	WebElement passwordEle;

	@FindBy(how = How.CSS, using = "button#asc-signin-btn")
	WebElement btn_SignIn;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Requisitions')]")
	WebElement requisitions;

	@FindBy(how = How.CSS, using = ".asc-list-name.highlight>p>a")
	WebElement userProfile;

	@FindBy(how = How.CSS, using = ".fa.fa-sort-down")
	WebElement arrw_ToSignout;

	@FindBy(how = How.CSS, using = "#asc-sign-out-button>i")
	WebElement btn_Signout;

	/*
	 * Login into application
	 * 
	 * @param : user name , password : String, String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void login(String userName, String password) throws InterruptedException {
		userNameEle.sendKeys(userName);
		passwordEle.sendKeys(password);
		btn_SignIn.click();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * Logout from the application
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void logout() throws InterruptedException {

		WaitFunctions.waitForElementToBeClickable(arrw_ToSignout);
		WaitFunctions.waitForElementToBeClickable(btn_Signout);
	}

}
