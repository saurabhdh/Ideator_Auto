package com.ideator.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenshot {

	
	public static void CaptureScreenShot(WebDriver driver, String ScreeenShotName) {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./src/main/resources/Screenshot/"+ScreeenShotName+".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception While taking Screenshot"+e.getMessage());
		}
	}
}


