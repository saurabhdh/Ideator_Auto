package com.ideator.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Test;



public class ConfigProperties {

	Properties properties;

	@Test
	public ConfigProperties() {
		try {
			File src = new File("./src/main/resources/config.properties");
			FileInputStream fis = new FileInputStream(src);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
		}
	}
	public String getApplicationURL() {
		String url = properties.getProperty("acceptanceurl");
		return url;
	}

	public String getWindowAuthencation() {
			String authencation = properties.getProperty("windowsAunthecationAcceptance");
			return authencation;
	}
}
