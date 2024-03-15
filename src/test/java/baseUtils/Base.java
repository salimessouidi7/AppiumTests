package baseUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	protected static AndroidDriver driver;
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService appiumService;
	private String appName;
	
	public Base(String appName) {
		this.appName = appName;
		
	}

	@BeforeClass(description = "Starts the Appium server automatically and initializes the Android driver for testing")
	public void startAppiumServer() throws MalformedURLException{

		// The path the Appium server executable
		String appiumPath = "C:\\Users\\TOPINFORMATIQUE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

		// Build the Appium service builder
		builder = new AppiumServiceBuilder().withAppiumJS(new File(appiumPath)).withIPAddress("127.0.0.1")
				.usingPort(4723).withTimeout(Duration.ofSeconds(300));

		// Start the Appium server
		appiumService = builder.build();
		appiumService.start();

		System.out.println("Appium server started successfully.");

		// Create capabilities
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setDeviceName("DemoDevice");
		cap.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\" + appName);

		// Set Appium server URL (default port 4723)
		URL url = new URL("http://127.0.0.1:4723/");

		// Launch the Android driver
		driver = new AndroidDriver(url, cap);

		// Set timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

		// Stop the Appium server after the test
		/*
		 * if (appiumService != null) { appiumService.stop(); }
		 */

	}

}
