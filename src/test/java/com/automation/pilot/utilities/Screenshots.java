package com.automation.pilot.utilities;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshots extends TestBaseClass {

	public static void getScreenshotsAs(String filepath) {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File(filepath));
		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

}
