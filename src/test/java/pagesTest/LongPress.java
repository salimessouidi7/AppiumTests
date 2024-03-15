package pagesTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import baseUtils.Base;
import io.appium.java_client.*;

public class LongPress extends Base {
	static WebDriverWait wait;
	
	public LongPress(String appName) {
		// Initialize with the name of the APK file for the application
		super("ApiDemos-debug.apk");
	}

	@Test(description = "Perform a long press action on an element")
	public void longPressTest() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

		//perform a long press
		WebElement pressElem = driver
				.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) pressElem).getId()), "duration", 2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]")));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]")).click();
		

	}

}
