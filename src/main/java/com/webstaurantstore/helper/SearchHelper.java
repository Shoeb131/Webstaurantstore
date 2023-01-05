package com.webstaurantstore.helper;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.webstaurantstore.home.SearchIteams;
import com.webstaurantstore.home.ShoppingCart;
import com.webstaurantstore.hooks.ApplicationHooks;
import com.webstaurantstore.utilities.DriverFactory;
import com.webstaurantstore.utilities.ElementUtil;

public class SearchHelper {
	private Logger log = LogManager.getLogger(this.getClass().getName());

	SearchIteams searchIteam = new SearchIteams(DriverFactory.getDriver());
	ShoppingCart shoppingCart;

	public void navigateToTheApplication() {
		DriverFactory.getDriver().get(ElementUtil.eu.getData("url"));
		ApplicationHooks.scenario.log(" - The Application launched successfully.");
		log.info(" - The Application launched successfully.");
	}

	public void searchIteamByText(String searchText) {
		searchIteam.searchIteam(searchText);
		searchIteam.clickOnTheSearchButton();
	}

	public List<String> verifySearchResults(String titleText) {
		log.info("Verifying the following titleText " + titleText);
		return searchIteam.openAllPagesAndVerifyProductTitle(titleText);
	}

	public void addLastSearchIteamOnTheCart() {
		shoppingCart = searchIteam.addLastSearchIteamOnTheCart();
	}

	public void emptyingTheShopingCart() {
		shoppingCart.clickingOnTheEmptyCartButton();
		shoppingCart.confirmingEmptyingCart();
	}
}
