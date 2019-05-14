package com.automation.pilot.pages;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.automation.pilot.utilities.Applib;
import com.automation.pilot.utilities.TestBaseClass;
import com.automation.pilot.utilities.WaitFunctions;

public class Acendify_PeoplesTab extends TestBaseClass {

	/*
	 * Locators for Ascendify Peoples Tab
	 */

	WebElement ele;

	@FindBy(how = How.CSS, using = "div[class='section-tab']")
	WebElement btn_mainMenu;

	@FindBy(how = How.CSS, using = ".link-text")
	WebElement link_fakeTitle;

	@FindBy(how = How.CSS, using = ".asc-list-more-information.right-panel-link>i")
	List<WebElement> arrows;

	@FindBy(how = How.CSS, using = "span#asc-show-next-opportunity-btn")
	WebElement btn_opportunity;

	@FindBy(how = How.CSS, using = "select#asc-next-opp-select")
	WebElement btn_sel_opp;

	@FindBy(how = How.XPATH, using = "//div[@class='chosen-container chosen-container-single']/a//span[text()='Please select an opportunity']")
	WebElement btn_sel_opp1;

	@FindBy(how = How.CSS, using = "#asc_next_opp_select_chosen > div > div > input[type=\"text\"]")
	WebElement txtBox_Search;

	@FindBy(how = How.XPATH, using = "//ul[@class='chosen-results']/li/em")
	WebElement option_txtSearch;

	@FindBy(how = How.XPATH, using = "//button[text()='Add User']")
	WebElement btn_AddUser;
	
	@FindBy(how = How.XPATH, using = "//input[@name='ascendify[address1]']")
	WebElement txtBox_Adress;

	@FindBy(how = How.CSS, using = "#asc-add-user-to-req-select")
	WebElement btn_reqs;

	@FindBy(how = How.XPATH, using = "//a[text()='My Open Requisitions']")
	WebElement btn_myOpenReq;

	@FindBy(how = How.XPATH, using = "//a[text()='Start']")
	WebElement btn_Start;

	@FindBy(how = How.CSS, using = ".asc-move-forward-btn.highlight-bg")
	WebElement btn_Forward;

	@FindBy(how = How.XPATH, using = "//button[text()='Interview Scheduler']")
	WebElement btn_IntScheduler;

	@FindBy(how = How.CSS, using = "#asc-interview-subtab-team")
	WebElement lbl_team_Int;

	@FindBy(how = How.XPATH, using = "//h1[text()='Setup Interview']")
	WebElement labl_InterviewSetup;
	
	@FindBy(how = How.XPATH, using = "//span[text()='NEXT']")
	WebElement btn_nxt;
	
	@FindBy(how = How.CSS, using = "span[class='muiList__item']")
	List<WebElement> list_menu_calendar;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Show the next 5 available times']")
	WebElement label_showMore;
	
	@FindBy(how = How.CSS, using = "span[class='muiList__item']>div>div>div:nth-child(2)")
	List<WebElement> label_date;
	
	@FindBy(how = How.CSS, using = "span[class='muiList__item']>div>div>div:nth-child(2)+div")
	List<WebElement> label_time;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Schedule Now']")
	WebElement btn_ScheduleNow;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Send']")
	WebElement btn_Send;
	
	@FindBy(how = How.XPATH, using = "//label[@class='muiList__item']/div/input")
	List<WebElement> list_chkBox_Calendar;

	@FindBy(how = How.XPATH, using = "//span[text()='EDIT']")
	WebElement btn_editIntv;

	@FindBy(how = How.CSS, using = "ul.info.hiring--int-list > li > div > div")
	List<WebElement> lbls_intvTeam;

	@FindBy(how = How.XPATH, using = "//ul[@class='interview-team--duration-block']/li/span")
	WebElement labl_IntvDuration;

	@FindBy(how = How.XPATH, using = "//div[@class='interview-details-wrapper']/a")
	WebElement labl_IntvphNum;

	@FindBy(how = How.XPATH, using = "///span[text()='***']")
	WebElement tab_peoplesTab;

	@FindBy(how = How.XPATH, using = "//span[text()='Team']/preceding-sibling::i")
	WebElement tab_Team;
	
	@FindBy(how = How.CSS, using = "div[class*='react-select__control']")
	WebElement txtBox_addCapability;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Select All']")
	WebElement btn_SelectAll;
	
	@FindBy(how = How.CSS, using = "div[class='meetingBlock']>div>span>div>div>span")
	List<WebElement> lst_interviewPanel;
	
	@FindBy(how = How.XPATH, using = "//span[text()='CLOSE']")
	WebElement btn_Close;
	
	@FindBy(how = How.XPATH, using = "//h2[text()='Scheduled Interview']")
	WebElement label_setUpIntv;

	Actions actions = new Actions(getDriver());

	// Below locators are useful to handle Selenium actions on dynamic data

	String tab_peoplesTab_xpath = "//span[text()='***']";
	String link_requisition_css = "a[data-original-title='***']>div";
	String link_arrow_info_xpath = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/i";
	String link_section_xpath = "//span[text()='***']/preceding-sibling::i";
	String link_arrow_ToDel = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/div/i";
	String link_ToDel_Req = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/div/div/span[@class='asc-tile-menu-item asc-list-delete-job link']";
	String link_ToDel_People = "//div[text()='***']/ancestor::div/div[contains(@class,'right')]/div/div/div/div/a[contains(@class,'delete-profile')]";
	String link_ToDel_Pipeline = "//div[text()='***']/following-sibling::div/div";
	String txtBox_PlaceHolder = "//input[@placeholder='***']";
	String option_fwd = "//ul[@class='dropdown-menu asc-split-dropdown-menu']/li/a[text()='***']";
	String label_CapValue = "//span[text()='***']";
	String txtBox_addCapValue = "//div[text()='***']/ancestor::div[@class='option-parent']/div/div/div/div[@class='capabilityDropDown']/input"; 
	String option_CapabilityCategory = "//div[text()='***']";
//	String option_Capability = "//div[contains(@class,'react-select__menu-list')]/div[text()='***']/parent::div";
	String option_Capability = "//div[text()='***']/parent::div/parent::div/parent::span";
	String divToCheck = "//div[text()='***']";
	String labelsToCheck = "//span[text()='***']";

	/*
	 * Entering into Menu Tab
	 * 
	 * @param : tabName - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void enterIntoMenuTab(String tabName) throws InterruptedException {
		WaitFunctions.waitForElementToBeClickable(btn_mainMenu).click();
		ele = Applib.getWebElementBasedOnText("xpath", tab_peoplesTab_xpath, tabName);
		ele.click();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * Clicking on Requisition
	 * 
	 * @param : Requisition Name - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return : None
	 */
	public void clickOnRequisition(String reqName) throws InterruptedException {
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("css", link_requisition_css, reqName));
		ele.click();
		Thread.sleep(3000);
	}

	/*
	 * Viewing Candidate Profile
	 * 
	 * @param : Candidate Name - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void viewCandidateProfile(String candidateName) throws InterruptedException {
		ele = WaitFunctions.waitForElementToBeClickable(
				Applib.getWebElementBasedOnText("xpath", link_arrow_info_xpath, candidateName));
		WaitFunctions.waitFor(1000);
		actions.moveToElement(ele).build().perform();
		ele.click();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * Select an opportunity
	 * 
	 * @param : Opportunity - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void selectAnOpportunity(String opportunity) throws InterruptedException {
		ele = WaitFunctions.waitForElementToBeClickable(btn_opportunity);
		ele.click();
		WebElement mySelectElement = WaitFunctions.waitForElementToBeClickable(btn_sel_opp1);
		WaitFunctions.waitTillElementIsVisible(mySelectElement).click();
		txtBox_Search.sendKeys(opportunity);
		WaitFunctions.waitTillElementIsVisible(option_txtSearch).click();

	}

	/*
	 * Select a tab for candidate
	 * 
	 * @param : tabName - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void selectATabForCandidate(String tabName) throws InterruptedException {
		WaitFunctions.waitForPageLoaded();
		ele = Applib.getWebElementBasedOnText("xpath", link_section_xpath, tabName);
		WaitFunctions.waitForElementToBeClickable(ele).click();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * Select a Team tab for candidate
	 * 
	 * @param : None
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void selectATeamTabForCandidate() throws InterruptedException {
		WaitFunctions.waitForPageLoaded();
		WaitFunctions.waitForElementToBeClickable(tab_Team).click();
		WaitFunctions.waitForPageLoaded();
	}

	/*
	 * deleting on Requisition
	 * 
	 * @param : Requisition Name - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void deleteRequisition(String jobTitle) throws InterruptedException {
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_arrow_info_xpath, jobTitle));
		Applib.mouseHoverAnelementForTime(ele, 3);
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_arrow_ToDel, jobTitle));
		ele.click();
		WaitFunctions.waitFor(2000);
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_ToDel_Req, jobTitle));
		ele.click();
		WaitFunctions.waitFor(2000);
		Applib.acceptAlert();
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
	public void deleteRecordInPeoplesTab(String jobTitle) throws InterruptedException {
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_arrow_info_xpath, jobTitle));
		Applib.mouseHoverAnelementForTime(ele, 3);
		ele.click();
		ele = WaitFunctions
				.waitForElementToBeClickable(Applib.getWebElementBasedOnText("xpath", link_arrow_ToDel, jobTitle));
		ele.click();
		ele = Applib.getWebElementBasedOnText("xpath", link_ToDel_People, jobTitle);
		ele.click();
		WaitFunctions.waitForAlert();
		Applib.acceptAlert();
	}

	/*
	 * Verify on Requisition presence
	 * 
	 * @param : Requisition Name - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public boolean verifyRequisionPresence(String jobTitle) {
		Applib.refreshPage();
		boolean flag = false;
		try {
			ele = WaitFunctions.waitForElementToBeClickable(
					Applib.getWebElementBasedOnText("xpath", link_arrow_info_xpath, jobTitle));
			flag = ele.isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/*
	 * create new people and move forward
	 * 
	 * @param : first Name - String
	 * 
	 * @Param : last Name - String
	 * 
	 * @Param : job to select - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void createANewPeopleAndMoveForward(String fName, String lName, String jobToSelect)
			throws InterruptedException {
		Applib.getWebElementBasedOnText("xpath", txtBox_PlaceHolder, "First Name").sendKeys(fName);
		;
		Applib.getWebElementBasedOnText("xpath", txtBox_PlaceHolder, "Last Name").sendKeys(lName);
		;
		Applib.scrollToElement(btn_AddUser);
		btn_myOpenReq.click();
		Applib.selectAnOption(btn_reqs, jobToSelect);
		btn_AddUser.click();
		WaitFunctions.waitFor(2000);
		WaitFunctions.waitForElementToBeClickable(btn_Forward).click();
		WaitFunctions.waitFor(500);
		Applib.getWebElementBasedOnText("xpath", option_fwd, "Phone Interview").click();
	}

	/*
	 * verifyInterviewSetUpInPeoplesTab
	 * 
	 * @param : interview Team - List
	 * 
	 * @param : interview duration - String
	 * 
	 * @param : phone number - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void verifyInterviewSetUpInPeoplesTab(List<String> expIntvTeamMem, String intvDuration, String expPhNum)
			throws InterruptedException {
		Applib.scrollToElement(btn_IntScheduler);
		// Verifying the title
		String actTitle = lbl_team_Int.getText();
		String expTitle = "Team";
		Assertion.assertEquals(actTitle, expTitle);
		logger.debug("Interview Titles are compared");
		// Verifying the interview Panel
		for (WebElement ele : lbls_intvTeam) {
			String txt = ele.getText();
			Assertion.assertTrue(expIntvTeamMem.contains(txt));
			logger.debug("Interview Team members are verified");
		}
		// Verifying the interview schedule
		Assertion.assertEquals(labl_IntvDuration.getText(), intvDuration);
		Assertion.assertEquals(labl_IntvphNum.getText(), expPhNum);
		Assertion.assertAll();
	}

	/*
	 * verify Interview Setup in PeoplesTab
	 * 
	 * @param : interview Team - List
	 * 
	 * @param : interview duration - String
	 * 
	 * @param : phone number - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void editInterviewSetUpInPeoplesTab(List<String> newIntvTeam, String intvDuration, String intvType)
			throws InterruptedException {
		Applib.scrollToElement(btn_IntScheduler);
		// Verifying the title
		btn_IntScheduler.click();
		WaitFunctions.waitForPageLoaded();
		Set<String> windows = getDriver().getWindowHandles();
		String childwindow = null;
		String parentwWndow = getDriver().getWindowHandle();
		for (String window : windows) {
			if (!window.equals(parentwWndow))
				childwindow = window;
		}
		getDriver().switchTo().window(childwindow);
		System.out.println(labl_InterviewSetup.getText());
		WaitFunctions.waitForPageLoaded();
		Applib.scrollToElement(btn_editIntv);
		btn_editIntv.click();
		// Verifying the interview Panel
		asc_requisitions.setUpInterViewTeam(intvType, intvDuration, newIntvTeam);
	}
	
	public List<String> verifyMeetingSetUpInPeoplesTab(int index) throws InterruptedException {
		List<String> resultList=new LinkedList<String>();
		WaitFunctions.waitForPageLoaded();
		WaitFunctions.waitFor(4000);
		WaitFunctions.waitTillElementIsVisible(label_showMore);
		WebElement element = WaitFunctions.waitForElementToBeClickable(list_menu_calendar.get(index));
		String date = label_date.get(index).getText();
		String time = label_time.get(index).getText();
		resultList.add(date);
		resultList.add(time);
		actions.doubleClick(element).perform();
//		WaitFunctions.waitTillElementIsVisible(list_chkBox_Calendar.get(index)).click();
		WaitFunctions.waitFor(2000);
		WaitFunctions.waitForElementToBeClickable(btn_ScheduleNow).click();
		WaitFunctions.waitFor(80000);
		WaitFunctions.waitTillElementIsVisible(btn_Send).click();
		WaitFunctions.waitFor(20000);
		return resultList;
	}
	
	public void clickNxtButton() throws InterruptedException
	{
		WaitFunctions.waitForElementToBeClickable(btn_nxt).click();
		
	}
	
	public void setUpMeeting() throws InterruptedException {
		Applib.scrollToElement(btn_IntScheduler);
		String parentwWndow = getDriver().getWindowHandle();
		btn_IntScheduler.click();
		String childwindow = null;
		Set<String> windows = getDriver().getWindowHandles();
		for (String window : windows) {
			if (!window.equals(parentwWndow))
				childwindow = window;
		}
		WaitFunctions.waitForPageLoaded();
		getDriver().switchTo().window(childwindow);
		WaitFunctions.waitForPageLoaded();
		WaitFunctions.waitFor(5000);
		WaitFunctions.waitForElementToBeClickable(btn_nxt).click();
		WaitFunctions.waitForPageLoaded();
		
	}
	
	/*
	 * create new people and move forward
	 * 
	 * @param : first Name - String
	 * 
	 * @Param : last Name - String
	 * 
	 * @Param : job to select - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void createANewPeopleAndMoveForward(String fName, String lName,String email,String address, String jobToSelect)
			throws InterruptedException {
		Applib.getWebElementBasedOnText("xpath", txtBox_PlaceHolder, "First Name").sendKeys(fName);
		Applib.getWebElementBasedOnText("xpath", txtBox_PlaceHolder, "Last Name").sendKeys(lName);
		WaitFunctions.waitFor(1000);
		Applib.getWebElementBasedOnText("xpath", txtBox_PlaceHolder,"Email").sendKeys(email);
		WaitFunctions.waitFor(1000);
		txtBox_Adress.sendKeys(address);
		Applib.scrollToElement(btn_AddUser);
		btn_myOpenReq.click();
		Applib.selectAnOption(btn_reqs, jobToSelect);
		Applib.scrollToElement(btn_AddUser);
		btn_AddUser.click();
		WaitFunctions.waitFor(5000);
		WaitFunctions.waitForPageLoaded();
		WaitFunctions.waitForElementToBeClickable(btn_Forward).click();
		WaitFunctions.waitFor(500);
		Applib.getWebElementBasedOnText("xpath", option_fwd, "Phone Interview").click();
	}
	
	/*
	 * create new people and move forward
	 * 
	 * @param : first Name - String
	 * 
	 * @Param : last Name - String
	 * 
	 * @Param : job to select - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void createCapabilities(Map<String,String> capabilities)
			throws InterruptedException {
		
		Set<String> capabilityTypes = capabilities.keySet();
		Applib.mouseHoverAnelementForTime(txtBox_addCapability, 2);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		for(String capability : capabilityTypes) {
			txtBox_addCapability.click();
//			WaitFunctions.waitFor(1000);
			WebElement ele=Applib.getWebElementBasedOnText("xpath", option_CapabilityCategory,capability);
			actions.moveToElement(ele).click().perform();
			WaitFunctions.waitFor(1000);	
			Applib.getWebElementBasedOnText("xpath", txtBox_addCapValue, capability).sendKeys(capabilities.get(capability));
			WaitFunctions.waitFor(1000);
			WebElement element =Applib.getWebElementBasedOnText("xpath", option_Capability, capabilities.get(capability));
			js.executeScript("arguments[0].scrollIntoView(true)",element);
//			element.click();
			js.executeScript("arguments[0].click()",element);
//			Applib.mouseHoverAnelementForTime(element, 1);
//			element.click();
//			actions.doubleClick(element).perform();
//			actions.moveToElement(element).click().perform();
//			js.executeScript("arguments[0];",element);
			WaitFunctions.waitFor(500);
//			actions.sendKeys(Keys.ENTER);
			
		}
		
	}
	
	/*
	 * Verify added Capabilities
	 * 
	 * @param : first Name - String
	 * 
	 * @Param : last Name - String
	 * 
	 * @Param : job to select - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void verifyCapabilities(Map<String,String> capabilities)
			throws InterruptedException {
		
		Set<String> capabilityTypes = capabilities.keySet();
		WaitFunctions.waitFor(2000);		
		for(String capability : capabilityTypes) {
			String actualCap = Applib.getWebElementBasedOnText("xpath", label_CapValue, capabilities.get(capability)).getText();
			Assertion.assertEquals(actualCap, capabilities.get(capability));
		}
		Assertion.assertAll();
		
	}
	

	/*
	 * Verify added Capabilities
	 * 
	 * @param : first Name - String
	 * 
	 * @Param : last Name - String
	 * 
	 * @Param : job to select - String
	 * 
	 * @author : gnana.kilambhi
	 * 
	 * @return :None
	 */
	public void setUpCapabilitiesFromPeoplesTab()
			throws InterruptedException {
//		WaitFunctions.waitForPageLoaded();
		WaitFunctions.waitFor(10000);
		WaitFunctions.waitForElementToBeClickable(btn_SelectAll).click();
		WaitFunctions.waitForElementToBeClickable(btn_nxt).click();	
		
	}
	
	public void reviewMeetingSchedule(List<String> intvTeam, String intDate, String intTime) throws InterruptedException
	{
		WaitFunctions.waitFor(6000);
		WaitFunctions.waitTillElementIsVisible(label_setUpIntv);
		for(WebElement element:lst_interviewPanel)
		{
			String intrvrName = element.getText();
			Assertion.assertTrue(intvTeam.contains(intrvrName));
		}
		ele = Applib.getWebElementBasedOnText("xpath", labelsToCheck,intDate);
		Assertion.assertTrue(ele.isDisplayed(), "Interview Date is not displayed");
//		ele = Applib.getWebElementBasedOnText("xpath", divToCheck,intTime);
//		Assertion.assertTrue(ele.isDisplayed(), "Interview Time is not displayed");
		btn_Close.click();
		Assertion.assertAll();
	}
}
