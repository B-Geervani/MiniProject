package com.generic;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static void getScreenshot(WebDriver driver,String name)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
	File temp=ts.getScreenshotAs(OutputType.FILE);
	File dest=new File(System.getProperty("user.dir")+"//Screenshots//"+name+System.currentTimeMillis()+".png");
	try {
		FileHandler.copy(temp,dest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
