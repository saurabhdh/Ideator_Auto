package com.ideator.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ideator.exception.PageException;
import com.ideator.util.ReflectionUtils;

public class IdeatorPage {
	WebDriver driver;
	private static final String TEXTBOX = "//input[@name='%s']";

	public IdeatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public IdeatorPage() {
		super();
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setText(WebElement we, String txt) {
		we.clear();
		we.sendKeys(txt);
	}

	protected void setText(By field, String txt) {
		try {
			WebElement element = getDriver().findElement(field);
			element.clear();
			element.sendKeys(txt);
		} catch (NoSuchElementException e) {
			throw new AssertionError("unable to set " + field, e);
		}
	}

	protected void setCheckbox(WebElement we, boolean check) {
		if ((we.isSelected() && !check) || (!we.isSelected() && check)) {
			we.click();
		}
	}

	public String getText(String fieldName) {
		return getDriver().findElement(By.xpath(String.format(TEXTBOX, fieldName))).getAttribute("value");
	}

	public boolean isChecked(WebElement input) {
		return StringUtils.isNotEmpty(input.getAttribute("checked"));
	}

	public void selectDropDownText(String fieldname, String value) {
		Select mySelectElm = new Select(getDriver().findElement(By.id(fieldname)));
		mySelectElm.selectByVisibleText(value);
	}

	public void acceptAlertIfPresent() {
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException ex) {
			// Alert not present do nothing
		}
	}

	public void setValues(List<List<String>> data, Object obj) {
		for (int count = 0; count < data.size(); count++) {
			String value = data.get(count).get(1);
			String field = data.get(count).get(0);
			ReflectionUtils.sendText(value, obj, field);
		}
	}

	public boolean isElementExist(By locator) {
		try {
			getDriver().findElement(locator);
		} catch (NoSuchElementException ex) {
			return false;
		}
		return true;
	}

	public void dragAndDrop(WebElement sourceElement, WebElement destinationElement) {
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			}
		} catch (NoSuchElementException e) {
			throw e;
		}
	}

	protected void clickButtonByValue(String name, By locator) {
		List<WebElement> buttons = getDriver().findElements(locator);
		for (WebElement button : buttons) {
			if (button.getAttribute("value").contains(name)) {
				submitAndCheckForErrorList(button);
				return;
			}
		}
		throw new AssertionError(name + " was not found on page!");
	}

	public void submitAndCheckForErrorList(WebElement we) {
		we.click();
		acceptAlertIfPresent();
		List<WebElement> elements = getDriver().findElements(By.className("validation"));
		if (elements != null && elements.size() >= 1) {
			try {
				WebElement errors = getDriver().findElement(By.className("errors"));
				List<WebElement> txts = errors.findElements(By.xpath("li/a"));
				StringBuilder sb = new StringBuilder();
				for (WebElement txt : txts) {
					sb.append(txt.getText()).append(",");
				}
				throw new PageException("Errors in submission of " + this + ": " + sb);
			} catch (NoSuchElementException ex) {
				// do nothing
			}
		}
	}

}
