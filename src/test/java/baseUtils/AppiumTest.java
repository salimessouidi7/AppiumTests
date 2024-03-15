package baseUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

public class AppiumTest {

	public AndroidDriver driver;
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService appiumService;

	@BeforeClass
	public void startAppiumServer() {

		// Set the path to your Appium server executable
		String appiumPath = "C:\\Users\\TOPINFORMATIQUE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

		// Build the Appium service builder (moved outside for reusability)
		builder = new AppiumServiceBuilder().withAppiumJS(new File(appiumPath)).withIPAddress("127.0.0.1")
				.usingPort(4723).withTimeout(Duration.ofSeconds(300));

		// Start the Appium server before the test
		appiumService = builder.build();
		appiumService.start();

		System.out.println("Appium server started successfully.");
	}

	@Test
	public void setUp() throws MalformedURLException {
		// Set the path to your Appium server executable
		String appiumPath = "C:\\Users\\TOPINFORMATIQUE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

		// Build the Appium service builder (moved outside for reusability)
		builder = new AppiumServiceBuilder().withAppiumJS(new File(appiumPath)).withIPAddress("127.0.0.1")
				.usingPort(4723).withTimeout(Duration.ofSeconds(300));

		// Start the Appium server before the test
		appiumService = builder.build();
		appiumService.start();

		// Create capabilities
		UiAutomator2Options cap = new UiAutomator2Options();

		cap.setDeviceName("DemoDevice");
		cap.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\ApiDemos-debug.apk");

		// Set Appium server URL (default port 4723)
		URL url = new URL("http://127.0.0.1:4723/");

		// Launch the Android driver
		driver = new AndroidDriver(url, cap);

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
