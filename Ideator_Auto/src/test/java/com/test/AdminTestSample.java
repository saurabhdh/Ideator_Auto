package com.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ideator.common.ConfigProperties;
import com.ideator.page.LoginPage;

public class AdminTestSample {
	WebDriver driver;

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
	// Login page test case.
	public void createIdeaWithoutLogin() {
	/*	LoginPage loginPage = new LoginPage(driver);
		loginPage.submitCredential();*/

		driver.findElement(By.xpath("//a[contains(text(),'Create new idea')]"))
				.click();
		driver.findElement(By.id("idea_name")).clear();
		driver.findElement(By.id("idea_name")).sendKeys("Selenium Test");
		WebElement location = driver.findElement(By.xpath(".//*[@id='idea_tagline']"));
		location.sendKeys("Temp Sample Tagline");
		driver.findElement(By.id("idea_description")).clear();
		driver.findElement(By.id("idea_description")).sendKeys(
				"Selenium Test pitch");
		driver.findElement(By.name("commit")).click();
		WebElement signupsection = driver.findElement(By.xpath(".//*[@id='ngdialog2']/div/div[1]/div[2]"));
		if (signupsection.isDisplayed()) {
			System.out.println("Signup is present");
		} else {
			System.out.println("Signup is missing");
		}
		driver.close();
	}

	@Test(priority = 2)
	// Login page test case.
	public void checkLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.submitLoginCredential();
		if (driver.findElement(By.cssSelector(".user-nav__action__icon"))
				.isDisplayed()) {
			System.out.println("Login Pass.");
		} else {
			System.out.println("Login fail. Please enter correct Login");
		}
		driver.close();
	}

//	@Test(priority = 4)
	// Idea creation
	public void IdeaCreation() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.submitLoginCredential();
		WebElement Idea = driver.findElement(By
				.xpath("//a[contains(text(),'Create new idea')]"));
		Idea.click();
		driver.findElement(By.id("idea_name")).clear();
		driver.findElement(By.id("idea_name")).sendKeys("Selenium Test Model");
		WebElement location = driver.findElement(By.xpath(".//*[@id='idea_tagline']"));
		location.sendKeys("Temp Sample Tagline");
		driver.findElement(By.id("idea_description")).clear();
		driver.findElement(By.id("idea_description")).sendKeys(
				"Selenium Test pitch");
		driver.findElement(By.name("commit")).click();
		driver.findElement(
				By.xpath(".//*[@id='ngdialog2']/div/div[1]/div[2]/div/div/div"))
				.click();
		driver.findElement(By.xpath("html/body/header/div[1]/div[3]/a[2]")).click();;
		driver.findElement(By.xpath("//a[contains(text(),'My Ideas')]"))
				.click();
		WebElement LoadMore = driver.findElement(By
				.xpath("//a[contains(text(),'Load More')]"));
		if (LoadMore.isDisplayed()) {
			LoadMore.click();
		}
		WebElement element = driver.findElement(By
				.xpath("//a[contains(text(),'Selenium Test Model')]"));
		element.click();
//		driver.close();
		System.out.println("Idea creation successfully done");
	}

	@Test(priority = 3)
	// Invite team member
	public void InviteTeamMember() {
		IdeaCreation();
//		WebElement inviteTeamMemberButton = driver.findElement(By
//				.xpath("//a[contains(text(),'Invite new team members')]"));
//		inviteTeamMemberButton.click();
		driver.findElement(By.linkText("Invite new team members")).click();
		driver.findElement(
				By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[2]/form/div[1]/div/tags-input/div/div/input"))
				.sendKeys("ochan@mailinator.com");
		driver.findElement(By.xpath(".//*[@id='invitations_personal_message']"))
				.sendKeys("Test");

		driver.findElement(By.xpath(".//*[@id='invitation_role_investor']"))
				.click();
		driver.findElement(
				By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[2]/form/div[5]/input"))
				.click();
		driver.findElement(By.cssSelector(".ng-modal-close")).click();
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		driver.get("https://www.mailinator.com/");
		WebElement sendInvite = driver.findElement(By
				.xpath(".//*[@id='inboxfield']"));
		sendInvite.sendKeys("ochan");
		driver.findElement(By.cssSelector(".btn.btn-dark")).click();
		List<WebElement> mails = driver.findElements(By
				.xpath("//div[2]/div[5]/div"));
		for (WebElement mail : mails) {
			if (mail.getText().contains(
					"You have been invited to join an idea on Ideator")) {
				mail.click();
				driver.switchTo().frame("publicshowmaildivcontent");
				String parentHandle = driver.getWindowHandle();// Current Window
				driver.findElement(By.xpath("//a[contains(text(),'Accept the invite')]")).click();
				System.out.println("Invite team member is successful.");
			}
			driver.close();
		}
	}
}
