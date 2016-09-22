package com.ideator.common;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class AuthencationAutoIt {
	
	@Test
	 public void test() throws IOException {
	  String [] param=new String[]{"C:\\Users\\saurabhdh\\Desktop\\Ideator Tickets\\AutoIt\\Authencation.exe","Authentication Required","rounaks","abcd"};
	  Runtime.getRuntime().exec(param);
	  //System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	  WebDriver driver =new FirefoxDriver();
	     driver.manage().window().maximize();
	     driver.get("http://cybagemis.cybage.com/Framework/Iframe.aspx");
	     driver.findElement(By.linkText("Human Resources")).click();
	 
	 }
}
