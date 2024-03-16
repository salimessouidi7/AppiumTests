package pagesTest;

import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.Base;

public class SetUpWifi extends Base {

	public SetUpWifi(String appName) {
		super("ApiDemos-debug.apk");
	}

	static WebDriverWait wait;

	@Test
	public void setWifiTest() {
		setWifi();
		
	}

}
