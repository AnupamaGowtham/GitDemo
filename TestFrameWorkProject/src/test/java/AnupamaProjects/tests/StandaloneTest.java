package AnupamaProjects.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AnupamaProjects.TestComponents.BaseTest;
import AnupamaProjects.pageObjects.CheckOut;
import AnupamaProjects.pageObjects.OrderPage;
import AnupamaProjects.pageObjects.ProductCatalog;
import AnupamaProjects.pageObjects.ProductConfirmation;

public class StandaloneTest extends BaseTest{

	String productName = "ZARA COAT 3";
		
		@Test(dataProvider="getData", groups= {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
	
		String country ="India";
		String confirmationMessage="Thankyou for the order.";
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("pwd"));
		
		productCatalog.getProductByName(input.get("productName"));
		productCatalog.addProductToCart(input.get("productName"));
		CheckOut checkOut = productCatalog.goToCartPage();
		
		boolean isMatch = checkOut.checkCartItems(input.get("productName"));
		Assert.assertTrue(isMatch);
		ProductConfirmation productConfirmation = checkOut.checkOutItems();
		String message = productConfirmation.getProductConfirmation(country);
		Assert.assertTrue(message.equalsIgnoreCase(confirmationMessage));
		Thread.sleep(2000);
			
		}
		
		//To Verify Zara Coat 3 is displaying in order page
		@Test(dependsOnMethods = {"submitOrder"})
		public void OrderHistoryTest() {
			//"ZARA COAT 3"
			ProductCatalog productCatalog = landingPage.loginApplication("anu$123@gmail.com", "Anu@12345");
			OrderPage orderPage = productCatalog.goToOrderPage();
			boolean isMatch = orderPage.verifyOrderDisplay(productName);
			Assert.assertTrue(isMatch);
		}
		
		
		

		@DataProvider
		public Object[][] getData() throws IOException {
			
			
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AnupamaProjects\\data\\PurchaseOrder.Json");
			 return new Object[][] {{data.get(0)}, {data.get(1)}}; 
		}
		
		
		
		 /* HashMap<String,String> map1  = new HashMap<String, String>();
		map1.put("email", "anu$123@gmail.com");
		map1.put("pwd", "Anu@12345");
		map1.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map2  = new HashMap<String, String>();
		map2.put("email", "gowtham$123@gmail.com");
		map2.put("pwd", "Abcd@1234");
		map2.put("productName", "adidas original"); */
		
	}


