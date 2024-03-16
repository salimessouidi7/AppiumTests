package pagesTest;

import org.testng.annotations.Test;
import baseUtils.Base;

public class LongPress extends Base {
	
	public LongPress(String appName) {
		// Initialize with the name of the APK file for the application
		
		super("ApiDemos-debug.apk");
	}

	@Test(description = "Perform a long press action on an element")
	public void longPressTest() {
		longPressEle();

	}

}
