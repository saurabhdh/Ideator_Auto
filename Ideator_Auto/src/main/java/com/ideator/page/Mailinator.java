package com.ideator.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.common.IdeatorPage;

public class Mailinator extends IdeatorPage {

	WebDriver driver;
	private LoginPage loginPage;

	@FindBy(xpath = ".//*[@id='inboxfield']")
	WebElement sendInvite;

	public Mailinator(WebDriver driver) {
		this.driver = driver;
		loginPage = new LoginPage(driver);
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, loginPage);
	}

	public void openMailinatorAndVerifyMail() {
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		driver.get("https://www.mailinator.com/");
		sendInvite.sendKeys("tempAutosdemo");
		driver.findElement(By.cssSelector(".btn.btn-dark")).click();
		List<WebElement> mails = driver.findElements(By.xpath("//div[2]/div[5]/div"));
		for (WebElement mail : mails) {
			if (mail.getText().contains("You have been invited to join a community")) {
				mail.click();
				break;
			}
		}
			driver.switchTo().frame("publicshowmaildivcontent");
			String parentHandle = driver.getWindowHandle();// Current Window
			driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]")).click();
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			loginPage.submitSignUpCredential();
			driver.close(); // close newly opened window when done with it
			driver.switchTo().window(parentHandle); // switch back to the original window
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
			driver.switchTo().window(oldTab); // switch back to the original window
		}

	}
