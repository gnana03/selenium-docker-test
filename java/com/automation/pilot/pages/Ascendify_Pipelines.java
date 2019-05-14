package com.automation.pilot.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.pilot.utilities.Applib;
import com.automation.pilot.utilities.TestBaseClass;
import com.automation.pilot.utilities.WaitFunctions;

public class Ascendify_Pipelines extends TestBaseClass {

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Job Function')]/following-sibling::i")
	WebElement arrow_jobFunction;

	@FindBy(how = How.CSS, using = ".inner-title-holder>i+div")
	WebElement text_innerMenuTitle;

	@FindBy(how = How.CSS, using = ".ascendicon-chevron-right.pipeline-arrow.rotateOpen.slideopen")
	WebElement arrow_innerJobFn;

	public WebElement getArrow_innerJobFn() {
		return arrow_innerJobFn;
	}

	public void setArrow_innerJobFn(WebElement arrow_innerJobFn) {
		this.arrow_innerJobFn = arrow_innerJobFn;
	}

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Role Name']")
	WebElement txtBox_RoleName;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Role Name']")
	WebElement labl_roleName;

	@FindBy(how = How.XPATH, using = "//select[@class='workflow-select required']")
	WebElement btn_Workflow;

	@FindBy(how = How.XPATH, using = "//button[text()='Save']")
	WebElement btn_Save;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'***')]/preceding-sibling::i[contains(@class,'arrow')]")
	WebElement btn_mainMenu;

	@FindBy(how = How.XPATH, using = "//ul[@class='asc-submenu-list clearfix pull-right']/li/i/following-sibling::span[text()='NEW JOB ROLE']")
	WebElement btn_NewJobRole;

	@FindBy(how = How.XPATH, using = "//div[@class='asc-trash open']/div/button[text()='Confirm']")
	WebElement button_Confirm_Pipeline;

	public WebElement getBtn_NewJobRole() {
		return btn_NewJobRole;
	}

	public void setBtn_NewJobRole(WebElement btn_NewJobRole) {
		this.btn_NewJobRole = btn_NewJobRole;
	}

	String xpath_mainMenu = "//div[contains(text(),'***')]/preceding-sibling::i[contains(@class,'arrow')]";
	String xpath_createdRole = "//div[text()='***']";
	String link_arrow_info_xpath = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/i";
	String link_ToDel_Pipeline = "//div[text()='***']/following-sibling::div/div";
	Actions actions = new Actions(getDriver());

	/*
	 * select Job Function
	 * 
	 * @param : categoryToSelect - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void selectJobFunction(String categoryToSelect) throws InterruptedException {
		WaitFunctions.waitForPageLoaded();
		arrow_jobFunction.click();
		System.out.println("Job Function is clicked");
		WaitFunctions.waitForPageLoaded();
		Applib.clickAnElementBasedOnText("xpath", xpath_mainMenu, categoryToSelect);
		WaitFunctions.waitForPageLoaded();
		text_innerMenuTitle.click();
		WaitFunctions.waitForPageLoaded();

	}

	/*
	 * select Created Role
	 * 
	 * @param : roleName - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void selectCreatedRole(String roleName) throws InterruptedException {
		// WaitFunctions.waitForPageLoaded();
		Applib.clickAnElementBasedOnText("xpath", xpath_createdRole, roleName);
	}

	/*
	 * Create a Role
	 * 
	 * @param : roleName - String
	 * 
	 * @param : workflow - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void createARole(String roleName, String workflow) throws InterruptedException {
		WaitFunctions.waitTillElementIsVisible(txtBox_RoleName).sendKeys(roleName);
		Applib.selectAnOption(btn_Workflow, workflow);
		actions.sendKeys(Keys.TAB, Keys.ENTER, Keys.F5).build().perform();
		logger.debug("Role is created " + roleName);
	}

	/*
	 * delete a record
	 * 
	 * @param : Requisition Name - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void deleteRecordInPipelines(String jobTitle) throws InterruptedException {
		WaitFunctions.waitFor(2000);
		WebElement ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_ToDel_Pipeline, jobTitle));
		ele.click();
		WaitFunctions.waitForElementToBeClickable(button_Confirm_Pipeline).click();

	}

}
