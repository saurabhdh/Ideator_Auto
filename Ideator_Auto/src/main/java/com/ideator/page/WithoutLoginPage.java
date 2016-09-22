package com.ideator.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WithoutLoginPage {

	WebDriver driver;
	
	@FindBy(xpath = "//a[contains(text(),'Create new idea')]")
	WebElement newIdeaButton;
	
	@FindBy(id = "idea_name")
	WebElement ideaName;
	
	@FindBy(id = "location")
	WebElement location;
	
	@FindBy(id = "idea_description")
	WebElement idea_description;
	
	@FindBy(name = "commit")
	WebElement commit;
	
	
	@FindBy(css = "option.ng-binding.ng-scope")
	WebElement category;
	
	
	@FindBy(xpath = ".//*[@id='ngdialog2']/div[2]/div[1]/div[2]")
	WebElement signupsection;

	public WithoutLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createIdeaWithoutLogin() {
		newIdeaButton.click();
		setText(ideaName, "Selenium Test");
		category.click();
		setText(location, "Pune");
		driver.findElement(By.id("location")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("html/body/div[2]/div[1]")).click();
		setText(idea_description, "Selenium Test pitch");
		commit.click();
		Assert.assertTrue(signupsection.isDisplayed());
	}

	private void setText(WebElement we, String txt) {
		we.clear();
		we.sendKeys(txt);
	}

}