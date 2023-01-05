package com.webstaurantstore.hooks;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.webstaurantstore.utilities.ConfigReader;
import com.webstaurantstore.utilities.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	private DriverFactory df;
	private WebDriver driver;
	private ConfigReader cr;
	public static Properties prop;
	public static Scenario scenario;


	@Before(order = 0)
	public void getProperty() {
		cr = new ConfigReader();
		prop = cr.init_prop();

	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		df = new DriverFactory();
		driver = df.init_driver(browserName);

	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		
		String screenshotName = scenario.getName().replaceAll(" ", "_");
		byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(sourcePath, "image/png", screenshotName);

	}

	@BeforeStep
	public void initializeScenario(Scenario scenario) {
		ApplicationHooks.scenario = scenario;
	}
}