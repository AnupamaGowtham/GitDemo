package AnupamaProjects.tests;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import AnupamaProjects.TestComponents.BaseTest;
import AnupamaProjects.TestComponents.Retry;
import AnupamaProjects.pageObjects.CheckOut;
import AnupamaProjects.pageObjects.ProductCatalog;

public class ErrorValidations extends BaseTest{

	
	
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
	
	landingPage.loginApplication("gowtham$123@gmail.com", "Anu@123456");
	String errorMessage = landingPage.getErrorMessage();
	Assert.assertEquals("Incorrect email or password.", errorMessage);
    
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	String productName = "ZARA COAT";
	ProductCatalog productCatalog = landingPage.loginApplication("anu$123@gmail.com", "Anu@12345");
	
	productCatalog.getProductByName(productName);
	productCatalog.addProductToCart(productName);
	CheckOut checkOut = productCatalog.goToCartPage();
	
	boolean isMatch = checkOut.checkCartItems("ZARA COAT 33");
	Assert.assertTrue(isMatch);
	
	
	
	}
}

