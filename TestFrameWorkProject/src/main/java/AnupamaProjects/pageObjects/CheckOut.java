package AnupamaProjects.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnupamaProjects.AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	By cart = By.cssSelector("button[routerlink*=cart]");
	
	public boolean checkCartItems(String productname ) {
		
		
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));
		boolean isMatch = cartItems.stream().anyMatch(item -> item.getText().contains(productname));
		return isMatch;
	}
	
	public ProductConfirmation checkOutItems() {
		checkOut.click();
		ProductConfirmation productConfirmation = new ProductConfirmation(driver);
		return productConfirmation;
	}
	
	

}
