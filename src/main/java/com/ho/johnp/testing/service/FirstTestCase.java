package com.ho.johnp.testing.service;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;;

public class FirstTestCase {

	public static void main (String args[]) {
		
		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		
        //Launch the Online Store Website
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
 
        		
        // Close the driver
        driver.quit();
	}	
}
