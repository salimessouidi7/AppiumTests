package pagesTest;

import org.testng.annotations.Test;

import baseUtils.Base;

public class SetUpWifi extends Base {

	public SetUpWifi(String appName) {
		super("ApiDemos-debug.apk");
	}

	@Test
	public void setWifiTest() {
		setWifi();
		
	}

}
