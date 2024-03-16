package baseUtils;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	static WebDriverWait wait;
	protected static AndroidDriver driver;
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService appiumService;
	private String appName;

	public Base(String appName) {
		this.appName = appName;

	}

	@BeforeClass(description = "Starts the Appium server automatically and initializes the Android driver for testing")
	public void startAppiumServer() throws MalformedURLException {

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
	
	/**
	 * This method used for GetTextFromApp class
	 */
	public void getTextFromEle() {
    	// Extract the text
    	String text = getElementText(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Media\"]"));
    	// Print the extracted text
    	Assert.assertEquals(text, "Media");
    	}

	/**
	 * This method used for setUpWifi class
	 */
	public void setWifi() {
		// Explicit wait for Preference element
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		clickByAccessibilityId("Preference");

		// Explicit wait for Preference dependencies element
		waitForElement(AppiumBy.accessibilityId("3. Preference dependencies"));
		
		clickByAccessibilityId("3. Preference dependencies");

		waitForElement(AppiumBy.id("android:id/checkbox"));
		clickById("android:id/checkbox");
		

		waitForElement(AppiumBy.xpath(
				"//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout"));
		clickByXPath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout");

		sendKeysById("android:id/edit", "Mr Unknown");
		clickById("android:id/button1");
	}

	/**
	 * This method used for LongPress class
	 */
	public void longPressEle() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		clickByAccessibilityId("Views");
		clickByAccessibilityId("Expandable Lists");
		clickByAccessibilityId("1. Custom Adapter");

		// perform a long press
		WebElement pressElem = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		longPressElement(pressElem, 2000);

		waitForElement(AppiumBy
				.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]"));
		
		clickByXPath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]");
	}

	/**
	 * Those methods used for ShopStoreApp class
	 * 
	 */
	public void personalInfo() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String fullName = "Salim Souidi";
		String country = "Algeria";

		// Select Personal Information
		clickByClassname("android.widget.Spinner");

		clickByXPath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + country + "\"]");

		waitForElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
		
		sendKeysById("com.androidsample.generalstore:id/nameField", fullName);

		clickById("com.androidsample.generalstore:id/btnLetsShop");
	}

	public void scrollDownGesture(String targetText) {
		// Scroll down repeatedly until the target item is found
		scrollToElementByText(targetText);

		// Click on the item using its xpath
		clickByXPath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]");
	}

	public void cartProductList() {
		clickById("com.androidsample.generalstore:id/appbar_btn_cart");
		waitForElement(AppiumBy.id("com.androidsample.generalstore:id/rvCartProductList"));
	}

	// Get number of item
	public int getCartProductCount() {
		// Xpath pattern for locating cart items
		String xpathPattern = "//android.support.v7.widget.RecyclerView[@resource-id='com.androidsample.generalstore:id/rvCartProductList']/android.widget.RelativeLayout[%d]/android.widget.LinearLayout";

		int itemCount = 0;
		int index = 1; // Start with the first index

		// Loop through the cart items until no more items are found
		while (true) {
			String xpath = String.format(xpathPattern, index);
			// Try to find the item using the constructed xpath
			try {
				driver.findElement(AppiumBy.xpath(xpath));
				// If the item is found, increment the count
				itemCount++;
				// Move to the next index
				index++;
			} catch (org.openqa.selenium.NoSuchElementException e) {
				// If no item is found, exit the loop
				break;
			}
		}
		// Return the total count of items in the cart
		return itemCount;
	}

	public void addItemToCart() {
		
		clickByXPath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]");
		clickByXPath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]");

		// Verify if the item was added successfully
		waitForElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]"));
		String addeditem = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]"))
				.getText();
		assertTrue(addeditem.contains("ADDED TO CART"), "The item '" + addeditem + "' is not selected.");
	}
	
	/**
	 * Those are Refactored methods to Refactor Common Actions into Methods
	 * Object Locators on Appium : xpath, id, classname, accessibilityid, androidUIautomator
	 */
	
	// Refactored method to perform explicit wait
	private void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Refactored method to find and click element by accessibility id
    private void clickByAccessibilityId(String accessibilityId) {
        driver.findElement(AppiumBy.accessibilityId(accessibilityId)).click();
    }

    // Refactored method to find and click element by xpath
    private void clickByXPath(String xpath) {
        driver.findElement(AppiumBy.xpath(xpath)).click();
    }
    
    // Refactored method to find and click element by id
    private void clickById(String id) {
        driver.findElement(AppiumBy.id(id)).click();
    }
    
 // Refactored method to find and click element by classname
    private void clickByClassname(String classname) {
        driver.findElement(AppiumBy.className(classname)).click();
    }

    // Refactored method to find and send keys to element by id
    private void sendKeysById(String id, String keys) {
        driver.findElement(AppiumBy.id(id)).sendKeys(keys);
    }
    
    private String getElementText(By locator) {
    	return driver.findElement(locator).getText();
    }

    // Refactored method to perform long press
    private void longPressElement(WebElement pressElem, int durationInMillis) {
    	((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) pressElem).getId()), "duration", durationInMillis);    }

    // Refactored method to scroll to element by text
    private void scrollToElementByText(String textElement) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + textElement + "\"))"));
    }

}
