package com.ho.johnp.testing.service;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ho.johnp.testing.service.vo.FileDataVO;
import com.ho.johnp.testing.service.vo.VehicleDetailsVO;

/**
 * The Testing Functionality would be stronger if it is 
 * modelled as a Junit Test.  
 * @author JohnP
 * 
 * IMPORTANT - Please execute this test with the appropriate webdriver installed for selenium.
 * Below is a copy of the system variable that is needed for this to execute properly. 
 * -Dwebdriver.gecko.driver=C:\\software\\geckodriver.exe
 */
public class TestVehicleData {

	@Test
	public void testDVLAVehicleData() {
		
		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		
		//Getting the list of File Data....
		FileFilter fileFilter = new FileFilter();
		List<FileDataVO> processedFiles = null;
		try {
			
			processedFiles = fileFilter.getVehicleDataForFiles(fileFilter);
			
		} catch (IOException ioException) {
			fail("An IO Exception should not be occuring at this point");
		}

		// Launch the Online Store Website
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
		
		//Perform the Submit with India as a Value
		WebElement startNow = driver.findElement(By.linkText("Start now"));
		startNow.click();
		
		//Wait Explicitly till the firstHeading element is found 
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		WebElement continueElement = 
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Continue")));
		
		WebElement vrmElement = driver.findElement(By.id("Vrm"));
		vrmElement.sendKeys(processedFiles.get(0).getVehicleDetails().get(0).getVrm());
				
		continueElement.click();
		
		WebDriverWait waitFor10Seconds = new WebDriverWait(driver, 10); 
		List<WebElement> listItems 
			= driver.findElements(By.className("list-summary-item"));
		
		boolean conditionsMatched = false;
		for (WebElement element : listItems) {
			
			String elementText = element.getText();
			VehicleDetailsVO vehicleDetailsVO 
				= processedFiles.get(0).getVehicleDetails().get(0);
						
			if (elementText.contains(vehicleDetailsVO.getVrm())  ||
				elementText.contains(vehicleDetailsVO.getMake()) || 
				elementText.contains(vehicleDetailsVO.getColor())) {
				
				//Test matched
				conditionsMatched = true;
				
			} else {
				
				conditionsMatched = false;
				
			}
		}
		assertTrue(conditionsMatched);
		
		// Close the driver
		driver.quit();

	}
}
