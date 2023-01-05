package com.webstaurantstore.utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * This method is used to initialize the threadlocal driver on the basis of
	 * given browser
	 * 
	 * @param browser the name of the browser in String
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(false);
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options));
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
			new Exception("The invalid browser name is provided");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
