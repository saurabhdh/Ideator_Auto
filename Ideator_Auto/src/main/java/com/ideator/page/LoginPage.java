package com.ideator.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.common.IdeatorPage;

public class LoginPage extends IdeatorPage {

	WebDriver driver;

	@FindBy(id = "user-email")
	WebElement userEmail;

	@FindBy(id = "user-password")
	WebElement userPass;

	@FindBy(xpath = ".//input[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement loginButton;

	@FindBy(id = "user-first-name")
	WebElement userFirstName;

	@FindBy(id = "user-last-name")
	WebElement userLastName;

	@FindBy(css = "input[name=commit]")
	WebElement submitSignupButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void submitLoginCredential() {
		loginButton.click();
		userEmail.sendKeys("gauravo@cybage.com");
		userPass.sendKeys("cybage@123");
		submitButton.click();
	}

	public void submitSignUpCredential() {
		setText(userFirstName, "First1");
		// userFirstName.clear();
		// userFirstName.sendKeys("First");
		userLastName.clear();
		userLastName.sendKeys("Last1");
		userEmail.sendKeys("vvvvva@mailinator.com");
		userPass.sendKeys("cybage@123");
		submitSignupButton.click();

	}

}