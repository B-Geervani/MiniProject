package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	 WebDriver driver;
	 WebDriverWait wait;
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// Home page button
	By Signup=By.id("signin2");
	By loginLink=By.id("login2");
    By Laptops=By.xpath("//a[text()='Laptops']");
	By Monitors=By.xpath("//a[text()='Monitors']");
	
	//Locators for SignUp button
    By signUser=By.id("sign-username");
	By signPass=By.id("sign-password");
	By signBtn=By.xpath("//button[text()='Sign up']");

	//Locators for LogIn
	By logiUn=By.id("loginusername");
	By loginPass=By.id("loginpassword");
	By loginBtn=By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
	
	//Locotors for Modal
	By signUpModal = By.id("signInModal");
    By loginModal = By.id("logInModal");
	
    public void clickSignUp() {
    	    
    	     ((JavascriptExecutor) driver).executeScript
    	     ("document.querySelector('#logInModal').style.display='none';");
    	
    	 WebElement signUpElement = driver.findElement(Signup);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
    }
    
     public void doSignup(String username, String password) {
			
		driver.findElement(signUser).sendKeys(username);
		driver.findElement(signPass).sendKeys(password);
		driver.findElement(signBtn).click();
		
		 try {
	            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	            alert.accept();
	        } catch (TimeoutException e) {
	            System.out.println("No alert appeared during sign-up.");
	        }
		}
	
	  public String dologin(String user,String pwd) {
		
		driver.findElement(loginLink).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginModal));
		driver.findElement(logiUn).sendKeys(user);
		driver.findElement(loginPass).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Login Alert: " + alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            
        }
	   return driver.getCurrentUrl();
	  }
	   
	public void displayCategories() throws InterruptedException  {
	
	    WebElement categorySection = driver.findElement(By.id("cat"));//find categories
	   List<WebElement> categories = categorySection.findElements(By.xpath("//a[@class='list-group-item']"));
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("window.scrollBy(0,350);");
	       Thread.sleep(1500);
	       
	    // Print all category names
	    for (WebElement category : categories) {
	        System.out.println(" - " + category.getText());
	    }
	}
	
	   public void selectLaptopAndAddToCart() throws InterruptedException {
	      
		   driver.findElement(Laptops).click();
	       System.out.println("Clicked on Laptops category");
	       
	       JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("window.scrollBy(0,500);");
	       Thread.sleep(1500);
	       
	       // Select MacBook Pro
	       WebElement macbookPro = driver.findElement(By.xpath("//a[text()='MacBook Pro']"));
	       macbookPro.click();
	       Thread.sleep(1500);
	       System.out.println("Clicked on MacBookPro");
	     
	       // Add to Cart
	       WebElement addToCartButton = driver.findElement(By.xpath("//a[text()='Add to cart']"));
	       addToCartButton.click();
	       Thread.sleep(1500);
	       System.out.println("Clicked on AddtoCart");

	       // Handle Alert Directly Here
	       try {
	    	    
	    	    Alert alert = driver.switchTo().alert();
	    	    System.out.println("Alert Text: " + alert.getText());
	    	    alert.accept();
	    	    System.out.println("Alert accepted");
	    	} catch (NoAlertPresentException e) {
	    	    System.out.println("No alert appeared after adding to cart");
	    	}
	   }
	     
	   public void clickHome() throws InterruptedException {
			driver.findElement(By.xpath("//a[text()='Home ']")).click();
			Thread.sleep(1500);
	   }
	   
	   public void selectMonitorsAndAddToCart() throws InterruptedException {
		   
		   driver.findElement(Monitors).click();
		   System.out.println("Clicked on Monitors category");
		 
		   JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("window.scrollBy(0,250);");
	      
	       WebElement ASUSFullHD = driver.findElement(By.xpath("//a[text()='ASUS Full HD']"));
	       ASUSFullHD.click();
	       Thread.sleep(1500);
	       System.out.println("Clicked on ASUSFullHD");
	       
	       WebElement addToCartButton = driver.findElement(By.xpath("//a[text()='Add to cart']"));
	       addToCartButton.click();
	       Thread.sleep(2000);
	       System.out.println("Clicked on AddtoCart");
	       
	       try {
	    	    
	    	    Alert alert = driver.switchTo().alert();
	    	    System.out.println("Alert Text: " + alert.getText());
	    	    alert.accept();
	    	    System.out.println("Alert accepted");
	    	} catch (NoAlertPresentException e) {
	    	System.out.println("No alert appeared after adding to cart");
	    	}
	      }
}