package AnupamaProjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnupamaProjects.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		//initialization code
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id= "userPassword")
	WebElement pwd;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="div[class*='ng-star-inserted']")
	WebElement errorMessage;
	
		
	public ProductCatalog loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		pwd.sendKeys(password);
		loginButton.click();
		System.out.println(email);
		System.out.println(password);
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		//waitForWebElementToAppear(errorMessage);
		String message = errorMessage.getText();
		return message;
	}
	

}
