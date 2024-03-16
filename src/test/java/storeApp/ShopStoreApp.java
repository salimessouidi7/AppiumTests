package storeApp;

import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.Base;

public class ShopStoreApp extends Base {
	static WebDriverWait wait;

	public ShopStoreApp() {
		super("General-Store.apk");
	}

	@Test(priority = 1)
	public void LoginStoreTest() {
		personalInfo();
	}

	@Test(description = "Add items to the shopping cart and verify successful addition",priority = 2)
	public void addItemToCartTest() {
		addItemToCart();
	}

	@Test(description = "Scroll to the item with specific text and select it", priority = 3)
	public void scrollToItemTest() {
		
		scrollDownGesture("Jordan Lift Off");
	}
	
	@Test(description = "Count the number of items in the cart", priority = 4)
	public void CartProductListTest() {
		cartProductList();
		// Print the count of items
        System.out.println("Number of items in the cart: " + getCartProductCount());
	}
	

}
