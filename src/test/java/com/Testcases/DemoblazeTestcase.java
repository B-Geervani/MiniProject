package com.Testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.generic.Utility;
import com.pages.CartPage;
import com.pages.HomePage;

public class DemoblazeTestcase {
	WebDriver driver;
	HomePage hp;
	CartPage cp;
	JavascriptExecutor js;
	
	
  @BeforeTest
  public void LanuchApp() {
	 
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.demoblaze.com/");
		js = (JavascriptExecutor) driver;
		hp = new HomePage(driver);
		cp = new CartPage(driver);
 }
  
  @Test(priority = 1)
  public void VerifySignUp() throws InterruptedException {
	  
	  hp.clickSignUp();
	  hp.doSignup("GEERVANI B","VANI1901");
	  Utility.getScreenshot(driver, "SignUp");  
  }
  
  @Test(priority = 2)
  public void VerifyLogin() throws InterruptedException{
	  
	  hp.dologin("GEERVANI B","VANI1901");
	  WebElement user = driver.findElement(By.id("nameofuser"));
      System.out.println(user.getText());
	  Utility.getScreenshot(driver, "Login");
      System.out.println("Current URL after login: " + driver.getCurrentUrl());
   }
  
  @Test(priority = 3)
  public void displayCategories() throws InterruptedException{
     
      hp.displayCategories();
      Utility.getScreenshot(driver, "CategoriesDisplayed");
  }

  @Test(priority = 4)
  public void selectLaptopAndAddToCart() throws InterruptedException {
      
	  hp.selectLaptopAndAddToCart();
      Utility.getScreenshot(driver, "LaptopAddedToCart");
      hp.clickHome();
  }
  
  @Test(priority=5)
  public void selectMonitorsAndAddToCart() throws InterruptedException { 
	  
	  hp.selectMonitorsAndAddToCart();
	  Utility.getScreenshot(driver, "MonitorAddedToCart");
  }
  
 @Test(priority=6)
  public void clickOnCart() throws InterruptedException {
	 
	  cp.clickOnCart();
      cp.DeleteItem();
	  cp.placeOrderBtn();
	  Utility.getScreenshot(driver, "placeOrderBtn");
	 }
 
 @Test(priority=7)
 public void OrderDetails() throws InterruptedException {
	 
	 cp.OrderDetails("vani",
			 "India",
			 "Bengaluru",
			 "123455",
			 "may",
			 "2024");
	 Thread.sleep(1000);
	 cp.logout();
	 Utility.getScreenshot(driver,"logout");
 }
 
 @AfterTest
  public void tearDown() {
      if (driver != null) {
          driver.quit();  
      }
    }
}
  
  



