package com.ExcelTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.generic.Utility;

public class OHRM1 {
	WebDriver driver;

	@Test(dataProvider = "exceldata", dataProviderClass = CustomData.class)
	public void login(String un, String psw) {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.name("username")).sendKeys(un);
		driver.findElement(By.name("password")).sendKeys(psw);
		Utility.getScreenshot(driver, "BeforeLogin_"+un);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utility.getScreenshot(driver, "AfterLogin_"+un);
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login Fail");
		System.out.println("Login Completed !!");
	}

	@Test(priority = 2)
	public void logout() {

		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
		driver.findElement(By.partialLinkText("Log")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Utility.getScreenshot(driver,"Logout");
		Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Logout Fail");
		System.out.println("Logout Successfully !!");
	}
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}