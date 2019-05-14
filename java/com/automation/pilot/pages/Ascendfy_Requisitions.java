package com.automation.pilot.pages;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import com.automation.pilot.utilities.Applib;
import com.automation.pilot.utilities.TestBaseClass;
import com.automation.pilot.utilities.WaitFunctions;

public class Ascendfy_Requisitions extends TestBaseClass {

	@FindBy(how = How.CSS, using = ".icon-li.grid-item>div>a>i.ascendicon-requisitions")
	WebElement tab_requisition;

	@FindBy(how = How.CSS, using = "span#asc-add-candidate>i")
	WebElement btn_newRequisition;

	@FindBy(how = How.CSS, using = "a#asc-add-req-manually")
	WebElement btn_addManually;

	@FindBy(how = How.XPATH, using = "//h4[text()='Add New Requisitions']")
	WebElement txt_newReq;

	@FindBy(how = How.XPATH, using = "//span[text()='Select Organization']/following-sibling::div/b")
	WebElement btn_selectOrg;

	@FindBy(how = How.XPATH, using = "//span[text()='Uncategorized']/following-sibling::div/b")
	WebElement btn_selectCategory;

	@FindBy(how = How.CSS, using = "input#asc-title")
	WebElement txtBox_title;

	@FindBy(how = How.CSS, using = "input#asc-posting-title")
	WebElement txtBox_PostingTitle;

	@FindBy(how = How.CSS, using = "input#asc-job-req")
	WebElement txtBox_jobId;

	@FindBy(how = How.CSS, using = ".caret")
	WebElement drpDown_Status;

	@FindBy(how = How.XPATH, using = "//a[text()='Open']")
	WebElement link_OPen;

	@FindBy(how = How.CSS, using = "span#status-name")
	WebElement labl_status;

	@FindBy(how = How.XPATH, using = "//button[text()='Continue']")
	WebElement btn_continue;

	@FindBy(how = How.CSS, using = "#interview-team-holder>.hiring--tab-titles>h5")
	WebElement txt_InterviewSetup;

	@FindBy(how = How.CSS, using = ".btn.btn-primary.setup-button")
	WebElement btn_InterviewSetup;

	@FindBy(how = How.XPATH, using = "//h1[text()='Setup Interview']")
	WebElement labl_InterviewSetup;

	@FindBy(how = How.XPATH, using = "//span[text()='EDIT']")
	WebElement btn_editIntv;

	@FindBy(how = How.XPATH, using = "//h1[text()='Setup Interview']/ancestor::div/section/h2")
	WebElement labl_ReqName;

	@FindBy(how = How.XPATH, using = "//label[text()='Interview Location']")
	WebElement labl_IntLocation;

	@FindBy(how = How.XPATH, using = "//label[text()='Interview Type']")
	WebElement labl_IntType;

	@FindBy(how = How.XPATH, using = "//label[text()='Duration']/parent::div/div/div/div[2]")
	WebElement labl_IntDuration;

	@FindBy(how = How.XPATH, using = "//span[text()='Add Interview']")
	WebElement btn_AddInt;

	@FindBy(how = How.CSS, using = "div[id*='InterviewLocation']> div:nth-child(1) > button > div > svg")
	WebElement btn_SelLocation;

	@FindBy(how = How.XPATH, using = "//input[@id='phoneLocation-']")
	WebElement txt_phoneLocation;

	@FindBy(how = How.XPATH, using = "//input[@id='otherLocation-']")
	WebElement txt_otherLocation;

	@FindBy(how = How.CSS, using = "div[id*='InterviewType'] > div:nth-child(1) > button > div > svg")
	WebElement drpDown_IntType;

	@FindBy(how = How.XPATH, using = "//div[text()='30 min']")
	WebElement txt_defaultDuration;

	@FindBy(how = How.CSS, using = "div[id*='undefined--Duration'] > div:nth-child(1) > button > div > svg")
	WebElement drpDown_duration;

	@FindBy(how = How.XPATH, using = "//span[text()='Add Interview']")
	WebElement btn_AddInterview;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Add Interviewer...']")
	WebElement txtBox_addInterviewer;

	@FindBy(how = How.CSS, using = "#saveInterviewStep > div > span")
	WebElement btn_SaveInterview;

	@FindBy(how = How.CSS, using = "#asc_pipeline_template_chosen > a > div")
	WebElement drpDown_jobTemplate;

	@FindBy(how = How.CSS, using = "#asc_organization_chosen  > a > div")
	WebElement drpDown_orgTemplate;

	@FindBy(how = How.CSS, using = "#asc_pipeline_template_chosen > div > div > input[type=\"text\"]")
	WebElement search_jobTemplate;

	@FindBy(how = How.CSS, using = "#asc_organization_chosen > div > div > input[type=\"text\"]")
	WebElement search_Org;

	@FindBy(how = How.XPATH, using = "//input[@data-original-title='Job Req #']")
	WebElement txtBox_JobReq;

	@FindBy(how = How.XPATH, using = "//div[@id='interview-team-holder']/div/h5/strong")
	WebElement labl_intvTeam;

	@FindBy(how = How.XPATH, using = "//div[@class='interview-scheduled-team']/div/ul/li/div/ul/li/div/div")
	List<WebElement> labls_intvTeamMems;

	@FindBy(how = How.XPATH, using = "//ul[@class='interview-team--duration-block']/li/span")
	WebElement labl_intvDuration;

	@FindBy(how = How.XPATH, using = "//div[@class='interview-details-wrapper']/a")
	WebElement labl_phNum;

	// Below locators are useful to handle Selenium actions on dynamic data

	String interviewLoc = "//div[text()='***']";
	String interviewText = "//textarea[@placeholder='***']";
	String drpDownValues = "//span[@role='menuitem']/div/div/div[text()='***']";
	String intvrDetails = "//span[@role='menuitem']/div/div/div/div/div[text()='***']";
	String jobTempToSelect = "//ul[@class='chosen-results']/li/em[text()='***']";
	String link_arrow_info_xpath = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/i";
	String link_arrow_ToDel = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/div/i";
	String link_ToDel_Req = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/div/div/span[@class='asc-tile-menu-item asc-list-delete-job link']";

	WebElement ele = null;

	/*
	 * description : Creating a requisition
	 * 
	 * @param : jobtitle - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : job Id
	 */

	public String createNewRequisition(String jobtitle) throws InterruptedException {
		String jobId = null;
		WaitFunctions.waitForElementToBeClickable(btn_newRequisition).click();
		WaitFunctions.waitForElementToBeClickable(btn_addManually).click();
		WaitFunctions.waitFor(3000);
		WaitFunctions.waitForElementAndText(txt_newReq, "Add New Requisitions");
		txtBox_title.sendKeys(jobtitle);
		jobId = txtBox_jobId.getAttribute("value");
		logger.debug("jobId " + jobId + " to be added");
		Applib.scrollToElement(btn_continue);
		WaitFunctions.waitForElementToBeClickable(btn_continue).click();
		logger.debug("jobId " + jobId + " is added");
		Thread.sleep(5000);
		return jobId;

	}

	/*
	 * description : Creating a requisition
	 * 
	 * @param : jobtitle - String
	 * 
	 * @param : jobId - String
	 * 
	 * @param : jobRole -String
	 * 
	 * @param : jobOrg - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : job Id
	 */

	public String createNewRequisition(String jobtitle, String jobId, String jobRole, String jobOrg)
			throws InterruptedException {
		WaitFunctions.waitForElementToBeClickable(btn_newRequisition).click();
		btn_addManually.click();
		WaitFunctions.waitFor(500);
		WaitFunctions.waitForElementAndText(txt_newReq, "Add New Requisitions");
		WaitFunctions.waitTillElementIsVisible(txtBox_title).sendKeys(jobtitle);
		WaitFunctions.waitTillElementIsVisible(txtBox_JobReq).sendKeys(jobId);
		WaitFunctions.waitTillElementIsVisible(drpDown_orgTemplate).click();
		WaitFunctions.waitTillElementIsVisible(search_Org).sendKeys(jobOrg);
		Applib.clickAnElementBasedOnText("xpath", jobTempToSelect, jobOrg);
		WaitFunctions.waitTillElementIsVisible(drpDown_jobTemplate).click();
		WaitFunctions.waitTillElementIsVisible(search_jobTemplate).sendKeys(jobRole);
		Applib.clickAnElementBasedOnText("xpath", jobTempToSelect, jobRole);
		jobId = txtBox_jobId.getAttribute("value");
		logger.debug("jobId " + jobId + " to be added");
		Applib.scrollToElement(btn_continue);
		WaitFunctions.waitForElementToBeClickable(btn_continue).click();
		logger.debug("jobId " + jobId + " is added");
		WaitFunctions.waitFor(2000);
//		WaitFunctions.waitTillElementIsVisible(btn_continue);
		return jobId;

	}

	/*
	 * description : verify InterView SetUp For MappedRole
	 * 
	 * @param : Interview team - List
	 * 
	 * @param : Interview Duration - String
	 * 
	 * @param : Interview phone number - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : job Id
	 */
	public void verifyInterViewSetUpForMappedRole(List<String> expIntvTeamMem, String intvDuration, String actPhNum)
			throws InterruptedException {
		Applib.scrollToElement(btn_InterviewSetup);
		// Verifying the title
		String actTitle = labl_intvTeam.getText();
		String expTitle = "Interview Team";
		Assertion.assertEquals(actTitle, expTitle);
		int count = labls_intvTeamMems.size();
		Assertion.assertTrue(count == expIntvTeamMem.size(), "Interview Panel members are not matched expected"+expIntvTeamMem.size() +" actual is"+labls_intvTeamMems.size());
		// Verifying the interview Panel
		for (WebElement ele : labls_intvTeamMems) {
			String txt = ele.getText();
			Assertion.assertTrue(expIntvTeamMem.contains(txt),"Interview team not mached for"+txt);
		}
		// Verifying the interview schedule
		Assertion.assertEquals(WaitFunctions.waitTillElementIsVisible(labl_intvDuration).getText(), intvDuration);
		Assertion.assertEquals(labl_phNum.getAttribute("data-content"), actPhNum);
	}

	/*
	 * description : enter Into Requisitons
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : job Id
	 */
	public void enterIntoRequisitons() throws InterruptedException {
		WaitFunctions.waitForElementToBeClickable(tab_requisition).click();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * description : verify InterView For New Req
	 * 
	 * @param : job title - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : job Id
	 */
	public void verifyInterViewForNewReq(String jobTitle) throws InterruptedException {
		Applib.scrollToElement(btn_InterviewSetup);
		String actualText = txt_InterviewSetup.getText();
		String expText = "Set up the interview for " + jobTitle;
		Assertion.assertEquals(actualText, expText);
		boolean flag = btn_InterviewSetup.isEnabled();
		Assertion.assertTrue(flag, "Interview Setup button is not enabled");
	}

	/*
	 * description : verify Interview SetUp Page For New Req
	 * 
	 * @param : jobTitle -String
	 * 
	 * @param : expintLocation -String
	 * 
	 * @param : expIntType - String
	 * 
	 * @param : expDuration - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : job Id
	 */
	public String verifyInterviewSetUpPageForNewReq(String jobTitle, String expintLocation, String expIntType,
			String expDuration) throws InterruptedException {
		String childwindow = null;
		String parentwWndow = getDriver().getWindowHandle();
		Applib.scrollToElement(btn_InterviewSetup);
		btn_InterviewSetup.click();
		Set<String> windows = getDriver().getWindowHandles();
		for (String window : windows) {
			if (!window.equals(parentwWndow))
				childwindow = window;
		}
		WaitFunctions.waitForPageLoaded();
		getDriver().switchTo().window(childwindow);
		WaitFunctions.waitForPageLoaded();
		// WaitFunctions.waitFor(2000);
		logger.debug(WaitFunctions.waitTillElementIsVisible(labl_InterviewSetup).getText());
		// WaitFunctions.waitFor(3000);
		String reqName = WaitFunctions.waitTillElementIsVisible(labl_ReqName).getText();
		String IntLocation = labl_IntLocation.getText();
		String IntType = labl_IntType.getText();
		String duration = labl_IntDuration.getText();
		Applib.scrollToElement(btn_AddInt);
		boolean flag = btn_AddInt.isEnabled();
		Assertion.assertEquals(IntLocation, expintLocation);
		Assertion.assertEquals(IntType, expIntType);
		Assertion.assertEquals(duration, expDuration);
		Assertion.assertTrue(flag);
		return reqName;
	}

	/*
	 * changeReqToOpen
	 * 
	 * @param : locToSelect - String
	 * 
	 * @param : textTosend - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void setUpIntvDetails(String locToSelect, String textTosend) throws InterruptedException

	{
		try {
			WaitFunctions.waitForPageLoaded();
			(btn_SelLocation).click();
			WebElement ele = Applib.getWebElementBasedOnText("xpath", interviewLoc, locToSelect);
			// Applib.selectAnOption(btn_SelLocation, locToSelect);
			WaitFunctions.waitForElementToBeClickable(ele).click();
			WaitFunctions.waitFor(500);
			if (locToSelect.equals("Web Interview")) {
				WebElement element = Applib.getWebElementBasedOnText("xpath", interviewText, "URL and other notes");
				String hiddenText = WaitFunctions.waitTillElementIsVisible(element).getAttribute("placeholder");
				WaitFunctions.waitForElementToBeClickable(element).sendKeys(textTosend);
				String actText = element.getText();
				Assertion.assertEquals(actText, textTosend);
				Assertion.assertEquals(hiddenText, "URL and other notes");
			} else if (locToSelect.equals("Phone Interview")) {
				String hiddenText = WaitFunctions.waitTillElementIsVisible(txt_phoneLocation)
						.findElement(By.xpath("preceding-sibling::label")).getText();
				txt_phoneLocation.sendKeys(textTosend);
				String actText = WaitFunctions.waitTillElementIsVisible(txt_phoneLocation).getText();
//				Assertion.assertEquals(actText, textTosend);
				Assertion.assertEquals(hiddenText, "Phone Number");
			} else if (locToSelect.equals("Other Location")) {
				String hiddenText = txt_otherLocation.findElement(By.xpath("preceding-sibling::label")).getText();
				txt_otherLocation.sendKeys(textTosend);
				String actText = txt_otherLocation.getText();
				Assertion.assertEquals(actText, textTosend);
				Assertion.assertEquals(hiddenText, "Location Name or Address");
			} else {
				Assertion.assertFalse(true, "Required default location is not provided");
			}
		} catch (Exception e) {
			Assert.assertFalse(true, "Exception is thrown " + e.getMessage());
		}
	}

	/*
	 * changeReqToOpen
	 * 
	 * @param : jobTitle - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public String changeReqToOpen(String jobTitle) throws InterruptedException {
		String status = null;
		drpDown_Status.click();
		WaitFunctions.waitFor(2000);
		link_OPen.click();
		WaitFunctions.waitFor(3000);
		status = labl_status.getText();
		return status;
	}

	/*
	 * setUpInterViewTeam
	 * 
	 * @param : Interview Team - List
	 * 
	 * @param : Interview Duration - String
	 * 
	 * @param : Interview Type - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void setUpInterViewTeam(String intType, String intDuration, List<String> intvrNames)
			throws InterruptedException {
		WaitFunctions.waitForElementToBeClickable(drpDown_IntType).click();
		WebElement element = Applib.getWebElementBasedOnText("xpath", drpDownValues, intType);
		WaitFunctions.waitForElementToBeClickable(element).click();
		WaitFunctions.waitForElementToBeClickable(drpDown_duration).click();
		element = Applib.getWebElementBasedOnText("xpath", drpDownValues, intDuration);
		element.click();
		for (String name : intvrNames) {
			WaitFunctions.waitForElementToBeClickable(txtBox_addInterviewer).sendKeys(name);
			WaitFunctions.waitFor(1000);
			element = WaitFunctions
					.waitTillElementIsVisible(Applib.getWebElementBasedOnText("xpath", intvrDetails, name));
			WaitFunctions.waitForElementToBeClickable(element).click();
		}
	}

	/*
	 * editInterViewTeam
	 * 
	 * @param : Interview Team - List
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void editInterViewTeam(List<String> intvrNames) throws InterruptedException {
		WaitFunctions.waitForElementToBeClickable(labl_InterviewSetup);
		Actions act = new Actions(getDriver());
		for (int i = 0; i < 4; i++) {
			act.sendKeys(Keys.DOWN);
		}
		WebElement element;
		for (String name : intvrNames) {
			txtBox_addInterviewer.sendKeys(name);
			WaitFunctions.waitFor(1000);
			element = WaitFunctions
					.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", intvrDetails, name));
			element.click();
			// WaitFunctions.waitFor(1000);

		}
	}

	/*
	 * addInterview
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void addInterview() throws InterruptedException {
		Applib.scrollToElement(btn_AddInterview);
		btn_AddInterview.click();
	}

	/*
	 * saveInterview
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void saveInterview() throws InterruptedException {
		Applib.scrollToElement(btn_SaveInterview);
		btn_SaveInterview.click();
		WaitFunctions.waitFor(500);
	}

	/*
	 * editInterviewSetUpInReqTab
	 * 
	 * @param : Interview Team - List
	 * 
	 * @param : Interview Duration - String
	 * 
	 * @param : Interview Type - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void editInterviewSetUpInReqTab(List<String> newIntvTeam, String intvDuration, String intvType)
			throws InterruptedException {
		Applib.scrollToElement(btn_InterviewSetup);
		// Verifying the title
		btn_InterviewSetup.click();
		WaitFunctions.waitForPageLoaded();
		Set<String> windows = getDriver().getWindowHandles();
		String childwindow = null;
		String parentwWndow = getDriver().getWindowHandle();
		for (String window : windows) {
			if (!window.equals(parentwWndow))
				childwindow = window;
		}
		getDriver().switchTo().window(childwindow);
		logger.debug(WaitFunctions.waitTillElementIsVisible(labl_InterviewSetup).getText());
		Applib.scrollToElement(WaitFunctions.waitTillElementIsVisible(btn_editIntv));
		btn_editIntv.click();
		// Verifying the interview Panel
		WaitFunctions.waitForPageLoaded();
		editInterViewTeam(newIntvTeam);
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
	public void deleteRecordInReqsnTab(String jobTitle) throws InterruptedException {
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_arrow_info_xpath, jobTitle));
		Applib.mouseHoverAnelementForTime(ele, 3);
		ele.click();
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_arrow_ToDel, jobTitle));
		ele.click();
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_ToDel_Req, jobTitle));
		ele.click();
		WaitFunctions.waitForAlert();
		Applib.acceptAlert();
	}

}
