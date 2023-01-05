package com.webstaurantstore.utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtil {
	public static GenericUtil gu = new GenericUtil();

	

	
	/**
	 * @param driver  driver the WebDriver instance
	 * @param element the WebElement
	 * @param time the time unit in second
	 */
	public void clickOnAWebElement(WebDriver driver, WebElement element, int time) {

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(time));
		explicitWait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

}

