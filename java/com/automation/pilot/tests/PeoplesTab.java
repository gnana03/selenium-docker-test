package com.automation.pilot.tests;

import org.testng.annotations.Test;

import com.automation.pilot.utilities.TestBaseClass;

public class PeoplesTab extends TestBaseClass {

	@Test(alwaysRun = true, description = " Login into peoples tab and verify candidate Information")
	public void verifyCandidateInformation() throws InterruptedException {
		String menuToSelect = "People Tab";
		String requisitionName = "Selenium <br /> NU2085";
		String candidateName = "Test Selenium";
		String oppToSelect = "CSV_Req_prod_01_again_Test2 | ST1235 | Santa Jose CA";
		String tabToSelect = "Responses";

		peoplesTab.enterIntoMenuTab(menuToSelect);
		peoplesTab.clickOnRequisition(requisitionName);
		peoplesTab.viewCandidateProfile(candidateName);
		peoplesTab.selectAnOpportunity(oppToSelect);
		peoplesTab.selectATabForCandidate(tabToSelect);

	}

}
