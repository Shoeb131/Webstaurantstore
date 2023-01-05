package com.webstaurantstore.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/webstaurantstore/features/SearchIteam.feature", glue = {
		"com.webstaurantstore.stepdefinations", "com/webstaurantstore/hooks" },

		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }

)

public class TestRunners extends AbstractTestNGCucumberTests {

}
