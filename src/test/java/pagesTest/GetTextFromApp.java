package pagesTest;

import org.testng.annotations.Test;

import baseUtils.Base;

public class GetTextFromApp extends Base {

	public GetTextFromApp(String appName) {
		super("ApiDemos-debug.apk");
	}

	@Test
	public void extractTextFromAppiumElement() {
		getTextFromEle();
	}

}
