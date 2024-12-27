package com.TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.pages.AdminPage;
import com.pages.LoginPage;

public class HrmTestcase {
	WebDriver driver;
	LoginPage lp;
	AdminPage ap;

	@Test
	public void LanuchBrowser() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		lp = new LoginPage(driver);
		ap = new AdminPage(driver);
	}

	@Test(priority = 1)
	public void doLogin() {
		lp.doLogin("Admin", "admin123");
		System.out.println("Login Pass !!");
	}

	@Test(priority = 2)
	public void verifyAdmin() {
		ap.getMenu();
	}

	@Test(priority = 3)
	public void VerifySearchWithUsername() {
		ap.searchEmpByUsername("Admin");
	}

	@Test(priority = 4)
	public void VerifySerchWithUserRole() {
		ap.searchEmpByUserRole("Admin");
	}

	@Test(priority = 5)
	public void VerifySerchWithUserStatus() {
		ap.searchEmpByStatus("Enabled");
	}

	@Test(priority = 6)
	public void logout() {

		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
		driver.findElement(By.partialLinkText("Log")).click();
		System.out.println("Logout Successfully !!");
	}
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
