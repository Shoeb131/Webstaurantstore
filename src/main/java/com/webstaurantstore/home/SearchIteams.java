package com.webstaurantstore.home;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.webstaurantstore.utilities.GenericUtil;

public class SearchIteams {
	@FindBy(id = "searchval")
	private WebElement searchBox;
	@FindBy(css = "button[value='Search']")
	private WebElement searchButton;
	@FindBy(css = "a[aria-label*='last page, page']")
	private WebElement lastPage;
	@FindBy(css = "a[href='/viewcart.cfm']")
	private WebElement viewCartButton;
	@FindBy(name = "addToCartButton")
	private List<WebElement> totalAddToCartCount;
	@FindBy(id = "details")
	private List<WebElement> allProducts;
	@FindBy(xpath = "//*[contains(@class,'inline-block leading-4 align-top')]")
	private List<WebElement> pageCount;
	@FindBy(css = "[class*='block font-semibold text-sm-1/2 leading-none mb-3 first']")
	private List<WebElement> productTitleList;
	@FindBy(name = "addToCartButton")
	private List<WebElement> allCartButtonList;
	@FindBy(css = "li[class*='inline-block leading-4 align-top rounded-r'] > a")
	private WebElement lastPageLink;
	@FindBy(css="[class*='inline-block leading-4 align-top']") List<WebElement> pagelinksList;

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private WebDriver driver;

	public SearchIteams(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchIteam(String iteamName) {
		log.info("Searching iteams with the text " + iteamName);
		searchBox.sendKeys(iteamName);
	}

	public void clickOnTheSearchButton() {
		log.info("Clicking on the Search button");
		GenericUtil.gu.clickOnAWebElement(driver, searchButton, 5);
	}

	public List<String> openAllPagesAndVerifyProductTitle(String titleText) {
		log.info("Verifying product title");
		int lastPageNumber = Integer.valueOf(lastPage.getText());

		ArrayList<String> missingTextTitleAll = new ArrayList<String>();
		for (int j = 1; j < lastPageNumber + 1; j++) {
			WebElement pageNumber = driver
					.findElement(By.xpath("//*[text() = '" + j + "' and contains(@aria-label,'page')]"));
			GenericUtil.gu.clickOnAWebElement(driver, pageNumber, 15);

			missingTextTitleAll.addAll(checkProductTitle(titleText));

		}
		return missingTextTitleAll;
	}

	public ShoppingCart addLastSearchIteamOnTheCart() {
		log.info("Adding the last search iteam on the Shopping Cart");

		int pcount = pageCount.size();
		if (lastPageLink.getAttribute("aria-label").contains("go to page")) {
			pcount = pcount - 1;
			WebElement lastPage = pagelinksList.get(pcount);
			GenericUtil.gu.clickOnAWebElement(driver, lastPage, 10);
		}

		addLastItemOfthePageToCart();
		return new ShoppingCart(driver);

	}

	private List<String> checkProductTitle(String titleText) {

		ArrayList<String> titleWithMissingTextList = new ArrayList<String>();
		for (int i = 0; i < allProducts.size(); i++) {

			String productTitle = productTitleList.get(i).getText();
			if (!productTitle.contains(titleText)) {
				titleWithMissingTextList.add(productTitle);
				System.out.println("missingtText productTitle" + productTitle);
				log.warn("The text doesnot exist on the title " + productTitle);

			}

		}

		return titleWithMissingTextList;

	}

	private void addLastItemOfthePageToCart() {
		WebElement lastItemNumber = allCartButtonList.get(totalAddToCartCount.size() - 1);
		GenericUtil.gu.clickOnAWebElement(driver, lastItemNumber, 10);
		GenericUtil.gu.clickOnAWebElement(driver, viewCartButton, 10);

	}
}
