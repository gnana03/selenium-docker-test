package com.automation.pilot.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.pilot.utilities.Applib;
import com.automation.pilot.utilities.RandomGenerator;
import com.automation.pilot.utilities.TestBaseClass;
import com.automation.pilot.utilities.TestDataRead;

public class Requisitions extends TestBaseClass {

	static String jobTitle = "Test Job to be deleted";
	static int randomNum = RandomGenerator.randaomNumGen(100000);
	static String jobId = Integer.toString(randomNum);
	static String jobToSelect;
	String jobRole = "test role" + RandomGenerator.randaomNumGen(1000);
	String phNum = "9848023238";
	String tabName = "Requisitions";
	String duration = "45 min";
	String submenuName, intrvType, intrvDuration, intrvLocation;
	String[] intrvTeam = TestDataRead.getDataFromTestDataProperty("INTERVIEW_TEAM_PANEL").split(";");
	List<String> intvrDetails = new ArrayList<String>(Arrays.asList(intrvTeam));
	String categoryToSelect = TestDataRead.getDataFromTestDataProperty("CATEGORY_PIPELINE");
	String xpath_createdRole = TestDataRead.getDataFromTestDataProperty("XPATH_CREATEDROLE");

	String filename = "TestData.xlsx";
	String filepath = "\\home\\ascendify\\eclipse-workspace\\seleniumqa-pilot\\Data";
	String sheetName = "Sheet1";

	/*
	 * description : Create a new requisition
	 */
	@Test(alwaysRun = true, description = "Verify InterView setup for new requisition")
	public void verifyInterViewSetUpForNewReq() {
		try {
			String tabName = "Requisitions";
			peoplesTab.enterIntoMenuTab(tabName);
			// asc_requisitions.enterIntoRequisitons();
			jobId = asc_requisitions.createNewRequisition(jobTitle);
			Applib.refreshPage();
			System.out.println("job is created with Id : " + jobId);
			jobToSelect = jobTitle;
			peoplesTab.viewCandidateProfile(jobToSelect);
			peoplesTab.selectATabForCandidate("Team");
			asc_requisitions.verifyInterViewForNewReq(jobTitle);
			Assertion.assertAll();
		} catch (Exception e) {
			Assertion.assertFalse(true);
		}
	}

	/*
	 * description : Verify Interview SetUp Page
	 */
	@Test(alwaysRun = true, dependsOnMethods = {
			"verifyInterViewSetUpForNewReq" }, description = "Create a new requisition")
	public void verifyInterViewSetupPageForNewReq() {
		try {
			asc_requisitions.enterIntoRequisitons();
			peoplesTab.viewCandidateProfile(jobToSelect);
			peoplesTab.selectATabForCandidate("Team");
			String actjobTitle = asc_requisitions.verifyInterviewSetUpPageForNewReq(jobTitle, "Interview Location",
					"Interview Type", "");
			Assertion.assertEquals(actjobTitle, jobTitle, "Assertion is failed");
			Assertion.assertAll();
		} catch (Exception e) {
			Assert.assertFalse(true);
		}
	}

	/*
	 * description : Verify Interview SetUp Page for default locations
	 */
	@Test(alwaysRun = true, dependsOnMethods = {
			"verifyInterViewSetUpForNewReq" }, description = "Verify default Interview locations for a new requisition")
	public void verifyDefaultIntLocations() {
		try {
			asc_requisitions.enterIntoRequisitons();
			peoplesTab.viewCandidateProfile(jobToSelect);
			peoplesTab.selectATabForCandidate("Team");
			asc_requisitions.verifyInterviewSetUpPageForNewReq(jobTitle, "Interview Location", "Interview Type", "");
			Applib.refreshPage();
			// asc_requisitions.verifyDefaultIntLocations("Web Interview",webURL);
			// asc_requisitions.verifyDefaultIntLocations("Phone Interview",phoneNumber);
			// asc_requisitions.verifyDefaultIntLocations("Other Location",address);
			Assertion.assertAll();
		} catch (Exception e) {
			Assert.assertFalse(true);
		}
	}

	/*
	 * description : Verify modifying the status of requisition
	 */
	@Test(alwaysRun = true, dependsOnMethods = {
			"verifyInterViewSetUpForNewReq" }, description = "Change the status of requisition to Open")
	public void makeRequisitionToOpen() throws InterruptedException {
		asc_requisitions.enterIntoRequisitons();
		peoplesTab.viewCandidateProfile(jobToSelect);
		String status = asc_requisitions.changeReqToOpen(jobToSelect);
		Assertion.assertEquals("Open", status);
		Assertion.assertAll();
	}

	/*
	 * description : Verify the interview setup for an Interview
	 */
	@Test(alwaysRun = true, dependsOnMethods = {
			"verifyInterViewSetUpForNewReq" }, description = "Set up the interview")
	public void setUpAnInterviewAndVerify() throws InterruptedException {
		// String jobToSelect="Selenium Test Job to be deleted - #OE1194";
		List<String> intvrDetails = new ArrayList<String>();
		intvrDetails.add("abc xyz");
		intvrDetails.add("Jason Zuniga");
		// Stream.of("abc xyz","Jason Zuniga").collect(Collectors.toList());
		String tabName = "Requisitions";
		peoplesTab.enterIntoMenuTab(tabName);
		// asc_requisitions.enterIntoRequisitons();
		peoplesTab.viewCandidateProfile(jobToSelect);
		peoplesTab.selectATabForCandidate("Team");
		asc_requisitions.verifyInterviewSetUpPageForNewReq(jobTitle, "Interview Location", "Interview Type", "");
		Applib.refreshPage();
		// asc_requisitions.verifyDefaultIntLocations("Web Interview",webURL);
		// asc_requisitions.setInterViewType("Technical","45 min",intvrDetails);
		asc_requisitions.saveInterview();
	}

	/*
	 * description : Verify delete requisition
	 */
	@Test(alwaysRun = true, dependsOnMethods = {
			"verifyInterViewSetUpForNewReq" }, description = "Delete a requisition")
	public void deleteRequisition() throws InterruptedException {
		asc_requisitions.enterIntoRequisitons();
		peoplesTab.viewCandidateProfile(jobToSelect);
		asc_requisitions.changeReqToOpen(jobToSelect);
		boolean present = peoplesTab.verifyRequisionPresence(jobToSelect);
		Assertion.assertFalse(present);
		Assertion.assertAll();
	}

}
