package com.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ideator.common.ConfigProperties;
import com.ideator.page.AdminPage;
import com.ideator.page.Homepage;
import com.ideator.page.LoginPage;
import com.ideator.page.SuperAdminPage;
import com.ideator.page.WithoutLoginPage;
import com.ideator.util.GetScreenshot;

public class AdminTest {

	WebDriver driver;
	Homepage homepage;
	LoginPage loginpage;
	AdminPage adminPage;

	@BeforeMethod
	public void setup() throws IOException, InterruptedException {
		ConfigProperties configproperties = new ConfigProperties();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Runtime.getRuntime().exec(configproperties.getWindowAuthencation());
		Thread.sleep(2000);
		Runtime.getRuntime().exec(configproperties.getWindowAuthencation());
		driver.get(configproperties.getApplicationURL());
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	//Add user to community and verify that on mailinator site.
	public void addUserCommunity() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		AdminPage adminPage = new AdminPage(driver);
		loginPage.submitLoginCredential();
		adminPage.ClickAdmin();
		adminPage.addUser();
		driver.close();
	}

//	@Test(priority = 2)
	//Check when custom OnbordingON.
	public void checkCustomOnBordingON() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		SuperAdminPage superAdminPage = new SuperAdminPage(driver);
		AdminPage adminPage = new AdminPage(driver);
		loginPage.submitLoginCredential();
		superAdminPage.SuperAdminCredential();
		superAdminPage.ClickSuperAdmin();
		superAdminPage.onBordingONCommunity();
		adminPage.onBordingON();
		driver.close();
	}

	@Test(priority = 1)
	//Check when custom OnbordingOFF.
	public void checkCustomOnBordingOFF() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		SuperAdminPage superAdminPage = new SuperAdminPage(driver);
		AdminPage adminPage = new AdminPage(driver);
		loginPage.submitLoginCredential();
		superAdminPage.SuperAdminCredential();
		superAdminPage.ClickSuperAdmin();
		superAdminPage.onBordingOFFCommunity();
		adminPage.onBordingOFF();
		driver.close();
	}

//		@Test(priority = 4)
	//Send Private message
	public void privateMessage() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		AdminPage adminPage = new AdminPage(driver);
		loginPage.submitLoginCredential();
		adminPage.ClickPrivateMessage();
		adminPage.sendMessage();
		driver.close();
	}
	
//	@Test(priority = 1)
	// Login page test case.
	public void ExecuteWithoutLogin() throws InterruptedException {
		WithoutLoginPage withoutLoginPage = new WithoutLoginPage(driver);
		withoutLoginPage.createIdeaWithoutLogin();
		driver.close();
	}

	
}