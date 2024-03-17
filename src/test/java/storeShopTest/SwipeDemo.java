package storeShopTest;

import org.testng.annotations.Test;

import baseUtils.Base;

public class SwipeDemo extends Base{

	public SwipeDemo(String appName) {
		super("ApiDemos-debug.apk");
	}
	
	@Test
	public void swipeTest() {
		swipeGesture();	
	}

}
