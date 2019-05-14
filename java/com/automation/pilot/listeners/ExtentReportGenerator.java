package com.automation.pilot.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.pilot.utilities.TestDataRead;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportGenerator implements ITestListener {
	String browserName = TestDataRead.getDataFromTestDataProperty("BROWSER");
	protected static ExtentReports reports;
	protected static ExtentTest test;

	public void onTestStart(ITestResult result) {
		System.out.println("on test start");
		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started in "+browserName);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("on test success");
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed in"+browserName);
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("on test failure");
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed in "+browserName);
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed",
				result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("on test skipped");
		test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("on test sucess within percentage");
	}

	public void onStart(ITestContext context) {
		System.out.println("on start");
		File f = new File(System.getProperty("user.dir") + "//ExtentReports");
		f.mkdir();
		reports = new ExtentReports(System.getProperty("user.dir") + "//ExtentReports//" + "extentReport_"
				+ new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + ".html", true);
		reports.addSystemInfo("Host Name", "QA Environment").addSystemInfo("Environment", "QA")
				.addSystemInfo("User Name", "Gnana Kilambhi")
				.addSystemInfo("Browser Name ",browserName);
		reports.loadConfig(new File(System.getProperty("user.dir") + "//extent-config.xml"));
	}

	public void onFinish(ITestContext context) {
		System.out.println("on finish");
		reports.endTest(test);
		reports.flush();
	}

}
