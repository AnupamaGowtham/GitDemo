package AnupamaProjects.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnupamaProjects.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderNames;
	
	
	public boolean verifyOrderDisplay(String productname ) {
		
		
		boolean isMatch = orderNames.stream().anyMatch(item -> item.getText().contains(productname.toLowerCase()));
		return isMatch;
	}
	
	
	

}
