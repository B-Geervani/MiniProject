package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	By username = By.name("username");
	By password = By.name("password");
	By loginBtn = By.xpath("//button[@type='submit']");

	public void doLogin(String un, String pwd) {
		driver.findElement(username).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
	}
}
