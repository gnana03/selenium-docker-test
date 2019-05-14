package com.automation.pilot.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import com.automation.pilot.utilities.Applib;
import com.automation.pilot.utilities.RandomGenerator;
import com.automation.pilot.utilities.TestBaseClass;
import com.automation.pilot.utilities.TestDataRead;
import com.automation.pilot.utilities.WaitFunctions;

public class MeetingSetUp extends TestBaseClass {
	String tab_Capability = TestDataRead.getDataFromTestDataProperty("TAB_CAPABILITY");
	String tab_Intv = TestDataRead.getDataFromTestDataProperty("TAB_INTERVIEW");
	String tab_Team = TestDataRead.getDataFromTestDataProperty("TAB_Team");
	String intrvType = TestDataRead.getDataFromTestDataProperty("INTV_TYPE");
	String intrvLocation = TestDataRead.getDataFromTestDataProperty("INTV_LOCATION");
	String intrvDuration = TestDataRead.getDataFromTestDataProperty("INTV_DURATION");
	String subMenu_pipelines = TestDataRead.getDataFromTestDataProperty("SUBMENU_PIPELINES");
	String subMenu_Req = TestDataRead.getDataFromTestDataProperty("SUBMENU_REQUISITIONS");
	String subMenu_People = TestDataRead.getDataFromTestDataProperty("SUBMENU_PEOPLE");
	String jobTitle = "Test Job to be deleted";
	int randomNum = RandomGenerator.randaomNumGen(100000);
	String new_IntvTeam = TestDataRead.getDataFromTestDataProperty("NEW_INTERVIEW_TEAM_PANEL");
	List<String> newIntvTeam = new ArrayList<String>(Arrays.asList(new_IntvTeam));
	String fName = TestDataRead.getDataFromTestDataProperty("FIRST_NAME");
	String lName = TestDataRead.getDataFromTestDataProperty("LAST_NAME") + RandomGenerator.randaomNumGen(100);
	String intvw_Team = TestDataRead.getDataFromTestDataProperty("INTERVIEW_TEAM_PANEL");
	String[] intvwMems = intvw_Team.split(";");
	List<String> intvrDetails = new ArrayList<String>(Arrays.asList(intvwMems));
	String workFlow_Pipelies = TestDataRead.getDataFromTestDataProperty("WORKFLOW_PIPELINES");
	String jobId = Integer.toString(RandomGenerator.randaomNumGen(100000));
	String jobToSelect;
	String jobRole = "test role" + RandomGenerator.randaomNumGen(1000);
	String durationToCheck = TestDataRead.getDataFromTestDataProperty("INTV_DURATION_TO_CHECK");
	String jobOrg = TestDataRead.getDataFromTestDataProperty("JOB_ORGANIZATION");
	String phNum = TestDataRead.getDataFromTestDataProperty("PHONE_NUMBER");
	String duration = TestDataRead.getDataFromTestDataProperty("INTV_DURATION");
	String newIntvType = TestDataRead.getDataFromTestDataProperty("INTV_TYPE");
	String newIntvDuration = TestDataRead.getDataFromTestDataProperty("NEW_INTV_DURATION");
	String categoryToSelect = TestDataRead.getDataFromTestDataProperty("CATEGORY_PIPELINE");
	String xpath_createdRole = TestDataRead.getDataFromTestDataProperty("XPATH_CREATEDROLE");
	String email = TestDataRead.getDataFromTestDataProperty("EMAIL_ID")+Integer.toString(RandomGenerator.randaomNumGen(10000))+"@testmail.com";
	String address = TestDataRead.getDataFromTestDataProperty("ADDRESS");
	String capabilities = TestDataRead.getDataFromTestDataProperty("CAPABILITIES");
	Map<String,String> capabilitiesMap = new LinkedHashMap<String,String>();
	
	// Verifying pipeline flow

	/*
	 * description : Create A Pipeline
	 * 
	 */

	@Test(alwaysRun = true, enabled = true, description = "Create A Pipeline and setup interview")
	public void createARoleInPipeline() throws InterruptedException, IOException {
		peoplesTab.enterIntoMenuTab(subMenu_pipelines);
		pipelines.selectJobFunction(categoryToSelect);
		pipelines.getBtn_NewJobRole().click();
		pipelines.createARole(jobRole, workFlow_Pipelies);
	}
	
	/*
	 * description : setup Capabilities for a created role
	 */

	@Test(alwaysRun = true, enabled = true, dependsOnMethods = {
			"createARoleInPipeline" }, description = "Create A Pipeline and setup interview")
	public void setupICapabilitiesForARole() throws InterruptedException {
		capabilitiesMap = Applib.createMapFromPropertyFile(capabilities);
		Applib.refreshPage();
		pipelines.selectJobFunction(categoryToSelect);
		pipelines.getArrow_innerJobFn().click();
		pipelines.selectCreatedRole(jobRole);
		peoplesTab.selectATabForCandidate(tab_Capability);
		peoplesTab.createCapabilities(capabilitiesMap);
		pipelines.selectCreatedRole(jobRole);
		peoplesTab.selectATabForCandidate(tab_Capability);
		peoplesTab.verifyCapabilities(capabilitiesMap);
		Assertion.assertAll();
	}


	/*
	 * description : setup interview for a created role
	 */

	@Test(alwaysRun = true, enabled = true, dependsOnMethods = {
			"setupICapabilitiesForARole" }, description = "Create A Pipeline and setup interview")
	public void setupInterviewForCreatedRole() throws InterruptedException {
//		Applib.refreshPage();
//		pipelines.selectJobFunction(categoryToSelect);
//		pipelines.getArrow_innerJobFn().click();
//		pipelines.selectCreatedRole(jobRole);
		peoplesTab.selectATabForCandidate(tab_Intv);
		asc_requisitions.verifyInterviewSetUpPageForNewReq(jobTitle, "Interview Location", "Interview Type", "30 min");
		asc_requisitions.setUpIntvDetails(intrvLocation, phNum);
		asc_requisitions.setUpInterViewTeam(intrvType, intrvDuration, intvrDetails);
		asc_requisitions.saveInterview();
		Applib.closeCurrentChildWindow();
		Applib.refreshPage();
		pipelines.selectJobFunction(categoryToSelect);
		pipelines.getArrow_innerJobFn().click();
		pipelines.selectCreatedRole(jobRole);
		peoplesTab.selectATabForCandidate(tab_Intv);
		asc_requisitions.verifyInterViewSetUpForMappedRole(intvrDetails, durationToCheck, phNum);
		Assertion.assertAll();
	}

	/*
	 * description : Create a Requisition and map created role
	 */

	@Test(alwaysRun = true, enabled = true, dependsOnMethods = {
			"setupInterviewForCreatedRole" }, description = "Create A requisition and map with a created role")
	public void createARequisitionAndMapARole() throws InterruptedException {
		capabilitiesMap = Applib.createMapFromPropertyFile(capabilities);
		jobToSelect = jobTitle + " - #" + jobId;
		peoplesTab.enterIntoMenuTab(subMenu_Req);
		asc_requisitions.createNewRequisition(jobTitle, jobId, jobRole, jobOrg);
		peoplesTab.selectATabForCandidate(tab_Capability);
		peoplesTab.verifyCapabilities(capabilitiesMap);
		peoplesTab.selectATeamTabForCandidate();
		asc_requisitions.verifyInterViewSetUpForMappedRole(intvrDetails, durationToCheck, phNum);
		Assertion.assertAll();
	}

	/*
	 * description : Edit Interview setup from Requisitions Tab
	 */

	@Test(alwaysRun = true, enabled = true, dependsOnMethods = {
			"createARequisitionAndMapARole" }, description = "verify Interview setup from Peoples Tab")
	public void EditIntvSetupFromRequisitionsTab() throws InterruptedException {
		asc_requisitions.editInterviewSetUpInReqTab(newIntvTeam, duration, intrvType);
		intvrDetails.add(new_IntvTeam);
		asc_requisitions.saveInterview();
		Applib.closeCurrentChildWindow();
		Applib.refreshPage();
		peoplesTab.viewCandidateProfile(jobToSelect);
		peoplesTab.selectATabForCandidate(tab_Team);
//		intvrDetails.addAll(Arrays.asList(new_IntvTeam));
		asc_requisitions.verifyInterViewSetUpForMappedRole(intvrDetails, durationToCheck, phNum);
	}

	/*
	 * description : verify Interview setup from Peoples Tab
	 */

	@Test(alwaysRun = true, enabled = true, dependsOnMethods = {
			"EditIntvSetupFromRequisitionsTab" }, description = "verify Interview setup from Peoples Tab")
	public void verifyMeetingSetupFromPeoplesTab() throws InterruptedException {
		
		intvrDetails.add(new_IntvTeam);
		Applib.refreshPage();
		peoplesTab.enterIntoMenuTab(subMenu_People);
//		jobToSelect = "test can12";
//		jobToSelect = jobTitle + " | " + jobId;
		String parentwWndow = getDriver().getWindowHandle();
		jobToSelect = jobId + " | " + jobTitle;
		Applib.clickAddManually();
		peoplesTab.createANewPeopleAndMoveForward(fName, lName,email,address, jobToSelect);
		peoplesTab.selectATabForCandidate(tab_Intv);
//		peoplesTab.editInterviewSetUpInPeoplesTab(intvrDetails, durationToCheck, phNum);
		peoplesTab.setUpMeeting();
		List<String> resultList = peoplesTab.verifyMeetingSetUpInPeoplesTab(0);
//		peoplesTab.clickNxtButton();
		peoplesTab.setUpCapabilitiesFromPeoplesTab();
		peoplesTab.reviewMeetingSchedule(intvrDetails, resultList.get(0), resultList.get(1));
		getDriver().switchTo().window(parentwWndow);
		Assertion.assertAll();
	}
	
	

	/*
	 * description : To delete created data
	 */
	@Test(alwaysRun = true, enabled = true, dependsOnMethods = {
			"verifyMeetingSetupFromPeoplesTab" }, description = "verify Interview setup from Peoples Tab")
	public void deleteCreatedData() throws InterruptedException {
		// Delete a record in peoples tab
		Applib.refreshPage();
		peoplesTab.deleteRecordInPeoplesTab(fName + " " + lName);
		Applib.refreshPage();
		// Delete a record in requisition tab
		WaitFunctions.waitForPageLoaded();
		peoplesTab.enterIntoMenuTab(subMenu_Req);
		jobToSelect = jobTitle + " - #" + jobId;
		asc_requisitions.deleteRecordInReqsnTab(jobToSelect);
		WaitFunctions.waitForPageLoaded();
		Applib.refreshPage();
		// Delete a record in pipelines tab
		peoplesTab.enterIntoMenuTab(subMenu_pipelines);
		pipelines.selectJobFunction(categoryToSelect);
		WaitFunctions.waitForElementToBeClickable(pipelines.getArrow_innerJobFn()).click();
		WaitFunctions.waitForPageLoaded();
		pipelines.selectCreatedRole(jobRole);
		pipelines.deleteRecordInPipelines(jobRole);
		Assertion.assertAll();

	}

}
