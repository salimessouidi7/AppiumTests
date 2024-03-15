package pagesTest;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LaunchGoogleChromeTest {

	public AndroidDriver driver;
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService appiumService;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set the path to your Appium server executable
		String appiumPath = "C:\\Users\\TOPINFORMATIQUE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

		// Build the Appium service builder (moved outside for reusability)
		builder = new AppiumServiceBuilder().withAppiumJS(new File(appiumPath)).withIPAddress("127.0.0.1")
				.usingPort(4723).withTimeout(Duration.ofSeconds(300));

		// Start the Appium server before the test
		appiumService = builder.build();
		appiumService.start();

		System.out.println("Appium server started successfully.");

		// Desired capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "DemoDevice"); // Replace if different
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "14.0"); // Replace with your emulator version
		capabilities.setCapability("udid", "emulator-5554");

		capabilities.setCapability("automationName", "UiAutomator2");

		// Set chrome app package and activity
		capabilities.setCapability("appPackage", "com.android.chrome");
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

		// Appium server URL (adjust port if needed)
		driver = new AndroidDriver(new java.net.URL("http://127.0.0.1:4723/"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testLaunchGoogleChrome() {
		driver.get("https://www.google.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Find the search bar element
		WebElement searchBar = driver.findElement(By.name("q"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Enter the search query
		searchBar.sendKeys("Appium with Selenium TestNG");

		// Submit the search (optional)
		searchBar.submit();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}