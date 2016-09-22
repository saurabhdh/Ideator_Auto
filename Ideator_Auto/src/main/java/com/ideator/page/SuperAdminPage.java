package com.ideator.page;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.common.ConfigProperties;

public class SuperAdminPage {
	WebDriver driver;

	@FindBy(css = ".user__image-small>img")
	WebElement avtar;

	@FindBy(xpath = "//a[contains(text(),'Communities')]")
	WebElement communityLink;

	@FindBy(xpath = "(//a[contains(text(),'Edit')])[11]")
	WebElement ucsdEditLink;

	@FindBy(id = "customOnboarding")
	WebElement customOnboard;

	@FindBy(id = "	ideatorLogoBranding")
	WebElement 	ideatorLogoBranding;


	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;

	public SuperAdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void SuperAdminCredential() throws IOException, InterruptedException {
		ConfigProperties configproperties = new ConfigProperties();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		Runtime.getRuntime().exec(configproperties.getWindowAuthencation());
		driver.get("https://acceptance.ideator.com");
	}

	public void ClickSuperAdmin() {
		avtar.click();
		List<WebElement> elementList = driver.findElements(By.cssSelector(".user-nav__link"));
		for (WebElement element : elementList) {
			if ((element.getText()).equals("Superadmin")) {
				element.click();
				break;
			}
		}
	}

	public void onBordingONCommunity() {
		communityLink.click();
		ucsdEditLink.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("customOnboarding"));
		js.executeScript("arguments[0].setAttribute('style', 'display:block')", element);
		setCheckbox(customOnboard, true);
		saveButton.click();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		driver.navigate().refresh();
	}

	public void onBordingOFFCommunity() {
		communityLink.click();
		ucsdEditLink.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("customOnboarding"));
		js.executeScript("arguments[0].setAttribute('style', 'display:block')", element);
		setCheckbox(customOnboard, false);
		saveButton.click();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		driver.navigate().refresh();
	}

	public void ideaAttributeCheck() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");

	}

	protected void setCheckbox(WebElement we, boolean check) {
		if ((we.isSelected() && !check) || (!we.isSelected() && check)) {
			we.click();
		}
	}
	
	private void onIdeatorLogo(WebElement we) {
		communityLink.click();
		ucsdEditLink.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("customOnboarding"));
		js.executeScript("arguments[0].setAttribute('style', 'display:block')", element);
		setCheckbox(we, false);
		saveButton.click();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		driver.navigate().refresh();
	}
	
	public void editLogo() {
		onIdeatorLogo(driver.findElement(By.xpath(".//*[@id='customBranding']")));
	}
}
