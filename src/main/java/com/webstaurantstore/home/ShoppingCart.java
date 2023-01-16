package com.webstaurantstore.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.webstaurantstore.utilities.GenericUtil;

public class ShoppingCart {
	@FindBy(xpath = "//*[text()='Empty Cart']")
	private WebElement emptyCartButton;
	@FindBy(xpath = "//footer[contains(@class,'bg-gray-100 border-gray-300 border-solid border-0')]/button[1]")
	private WebElement confirmingEmptyCartButton;
	private Logger log = LogManager.getLogger(this.getClass().getName());
	private WebDriver driver;

	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickingOnTheEmptyCartButton() {
		log.info("Clicking on the Empty Cart Button");
		GenericUtil.gu.clickOnAWebElement(driver, emptyCartButton, 10);
	}

	public void confirmingEmptyingCart() {
		log.info("Confirming the Emptying the Cart");
		GenericUtil.gu.clickOnAWebElement(driver, confirmingEmptyCartButton, 10);
	}
}
