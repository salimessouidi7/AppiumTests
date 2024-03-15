package pagesTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseUtils.Base;
import io.appium.java_client.AppiumBy;

public class GetTextFromApp extends Base {

	public GetTextFromApp(String appName) {
		super("ApiDemos-debug.apk");
	}

	@Test
	public void extractTextFromAppiumElement() {

		// Extract the text
		String text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Media\"]")).getText();

		// Print the extracted text
		Assert.assertEquals(text, "Media");
	}

}
