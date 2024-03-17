package storeShopTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseUtils.Base;
import io.appium.java_client.AppiumBy;

public class ToastMessageTest extends Base{
	
	public ToastMessageTest(String appName) {
		super("General-Store.apk");
	}

	@Test
	public void toastMsgTest() {
		// DRY (Don't Repeat Yourself)
		
		/**
		 * Calling the method by passing an empty string to skip the part of entering the name
		 * To trigger the exception (with the error message indicating a missing obligatory field)
		 */
		personalInfo("");
		
		// Toast message
		String toastMsg = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getAttribute("name");
		Assert.assertEquals(toastMsg, "Please enter your name");
	}

}
