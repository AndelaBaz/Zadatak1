package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.ExtentManager;
import Base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(Resources.Listeners.class)
public class AddRemoveItemBasketTest extends Hooks {

	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void addRemove() throws IOException, InterruptedException {

		ExtentManager.log("Starting AddRemoveItemBasketTest ...");

		Homepage home = new Homepage();
		home.getCookie().click();
		home.getTestStoreLink().click();
		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("Reached the Shop Homepage");
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added product to basket");
		Thread.sleep(5000);

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		waitForElementInvisible(cart.getDeleteItemTwo(), 10);

		System.out.println(cart.getTotalAmount().getText());

		try {
			// using an assertion to make sure the total amount is what we are expecting
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.23");
			ExtentManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found" + cart.getTotalAmount().getText()
					+ " expected $45.23");
			ExtentManager.fail("The total amount did not match the expected amount.");
		}

	}

}
