package storeApp;

import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;

import baseUtils.Base;

public class ShopStoreApp extends Base {
	static WebDriverWait wait;

	public ShopStoreApp() {
		super("General-Store.apk");
	}

	@Test(priority = 1)
	public static void LoginStoreTest() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String fullName = "Salim Souidi";
		String country = "Algeria" ;

		// Select Personal Information
		driver.findElement(AppiumBy.className("android.widget.Spinner")).click();

		// Wait for element to be selected
		// wait.until(ExpectedConditions.elementToBeSelected(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"
		// and @text=\"Algeria\"]")));

		driver.findElement(
				AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" +country+ "\"]")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/nameField")));
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys(fullName);

		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}

	@Test(description = "Add items to the shopping cart and verify successful addition",priority = 2)
	public static void addItemToCart() {

		driver.findElement(AppiumBy.xpath(
				"(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")).click();
		driver.findElement(AppiumBy.xpath(
				"(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

		// Verify if the item was added successfully
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]")));
		String addeditem = driver.findElement(AppiumBy.xpath(
			    "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]")).getText();
		assertTrue(addeditem.contains("ADDED TO CART"), "The item '" + addeditem + "' is not selected.");

	}

	@Test(description = "Scroll to the item with specific text and select it", priority = 3)
	public void scrollToItemTest() {
		
		String targetText = "Jordan Lift Off";

		// Scroll down repeatedly until the target item is found
		driver.findElement(AppiumBy.androidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + targetText + "\"))"));

		// Click on the item using its xpath
		driver.findElement(AppiumBy.xpath(
			"(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();
	}
	
	@Test(description = "Count the number of items in the cart", priority = 4)
	public void CartProductList() {
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/rvCartProductList")));
		// Print the count of items
        System.out.println("Number of items in the cart: " + getCartProductCount());
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

}
