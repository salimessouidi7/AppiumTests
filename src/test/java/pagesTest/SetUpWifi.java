package pagesTest;

import org.testng.annotations.*;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.Base;
import io.appium.java_client.AppiumBy;

public class SetUpWifi extends Base {

	public SetUpWifi(String appName) {
		super("ApiDemos-debug.apk");
	}

	static WebDriverWait wait;

	@Test
	public void setWifiTest() {
		// Object Locators on Appium : xpath, id, classname, accessibilityid, androidUIautomator

		// Explicit wait for Preference element
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();

		// Explicit wait for Preference dependencies element
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.accessibilityId("3. Preference dependencies")));
		
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies"))
				.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/checkbox")));
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")));
		driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
		
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Mr Unknown");
		//driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		
	}

}
