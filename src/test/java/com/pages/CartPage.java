package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	WebDriver driver;
	
	//Cart Locator
	By cartMenu = By.id("cartur");
	
   // Place Order Form Fields
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By creditCardField = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By purchaseButton = By.xpath("//button[contains(text(),'Purchase')]");
    By confirmationOK = By.xpath("//button[text()='OK']");
	By logout =By.id("logout2");
	
	public CartPage(WebDriver driver) {
        this.driver = driver;
    }
	
	 public void clickOnCart() {
	 driver.findElement(cartMenu).click();
		 }
	 
	 public void DeleteItem() throws InterruptedException {
		 driver.findElement(By.xpath("//td[text()='ASUS Full HD']/following-sibling::td//a[text()='Delete']")).click();
		 System.out.println("ASUS Full HD deleted successfully.");
		 Thread.sleep(3000);
	 }
	 
	 public void placeOrderBtn() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		Thread.sleep(2000);
		 }
	 
	// Fill Order Details and Purchase
	    public void OrderDetails(String name, String country, String city, String card, String month, String year) throws InterruptedException {
	        driver.findElement(nameField).sendKeys(name);
	        driver.findElement(countryField).sendKeys(country);
	        driver.findElement(cityField).sendKeys(city);
	        driver.findElement(creditCardField).sendKeys(card);
	        driver.findElement(monthField).sendKeys(month);
	        driver.findElement(yearField).sendKeys(year);
	        driver.findElement(purchaseButton).click();
	        Thread.sleep(2000);
	        driver.findElement(confirmationOK).click();
	        }

		public void logout() {
			driver.findElement(logout).click();
	        System.out.println("Logged out successfully");
			}
}
