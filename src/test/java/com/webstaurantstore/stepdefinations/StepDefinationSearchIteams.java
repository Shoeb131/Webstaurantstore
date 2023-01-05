package com.webstaurantstore.stepdefinations;

import static org.testng.Assert.assertTrue;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.webstaurantstore.helper.SearchHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinationSearchIteams {
	private Logger log = LogManager.getLogger(this.getClass().getName());
	SearchHelper searchHelper = new SearchHelper();

	@Given("I navigate to the webstaurantstore site")
	public void i_navigate_to_the_webstaurantstore_site() {
		searchHelper.navigateToTheApplication();
	}

	@When("I search for {string}")
	public void i_search_for_stainless_work_table(String searchText) {
		searchHelper.searchIteamByText(searchText);
	}

	@When("Verify search result has the word {string} in its title")
	public void verify_search_result_has_the_word_table_in_its_title(String titleText) {
		List<String> theFinalMissingTextTitleList = searchHelper.verifySearchResults(titleText);
		log.warn("The following titles (if the list is not empty) are missing expected text "+ theFinalMissingTextTitleList);
		
		assertTrue(theFinalMissingTextTitleList.isEmpty(),
				"Verify the given title exists on the search iteams " + theFinalMissingTextTitleList);

	}

	@When("Add the last of found items to Cart")
	public void add_the_last_of_found_items_to_cart() {
		searchHelper.addLastSearchIteamOnTheCart();

	}

	@Then("I Empty the Cart")
	public void i_empty_the_cart() {
		searchHelper.emptyingTheShopingCart();
	}

}
