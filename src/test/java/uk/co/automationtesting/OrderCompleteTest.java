package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.ExtentManager;
import Base.Hooks;
import pageObjects.Homepage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
//import pageObjects.Homepage;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;
@Listeners(Resources.Listeners.class)
public class OrderCompleteTest extends Hooks {

	public OrderCompleteTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void endToEndTest() throws InterruptedException, IOException {
		
		ExtentManager.log("Starting OrderCompleteTest...");
		
		Homepage home = new Homepage();
		ExtentManager.pass("Have successfully reached store homepage");
		home.getCookie().click();
		home.getTestStoreLink().click();
		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Have successfully reached shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added item to cart");

		Thread.sleep(5000);

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getCheckoutBtn().click();

		ShoppingCart sCart = new ShoppingCart();
		ExtentManager.pass("Have successfully reached the shopping cart page");
		sCart.getHavePromo().click();
		ExtentManager.pass("Have successfully selected the promo button");
		sCart.getPromoTextbox().sendKeys("20OFF");
		sCart.getPromoAddBtn().click();
		sCart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Have successfully selected the check out button");

		OrderFormPersInfo formaPers = new OrderFormPersInfo();
		formaPers.getGenderMrs().click();
		formaPers.getFirstNameField().sendKeys("Korsinik");
		formaPers.getLastnameField().sendKeys("Prezime");
		formaPers.getEmailField().sendKeys("tesst@test.com");
		formaPers.getTermsConditionsCheckbox().click();
		formaPers.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered customer details");
		Thread.sleep(3000);

		OrderFormDelivery formaDel = new OrderFormDelivery();
		formaDel.getAddressField().sendKeys("Adresa 5");
		formaDel.getCityField().sendKeys("Split");
		Select menuItem = new Select(formaDel.getStateDropdown());
		menuItem.selectByVisibleText("Texas");
		formaDel.getPostcodeField().sendKeys("50613");
		formaDel.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered delivery info");
		Thread.sleep(5000);

		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();
		ExtentManager.pass("Have successfully selected the shipping method");
		Thread.sleep(5000);

		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Have successfully placed order");
		Thread.sleep(5000);

	}
}
