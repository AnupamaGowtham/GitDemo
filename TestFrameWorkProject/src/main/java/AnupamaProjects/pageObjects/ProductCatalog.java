package AnupamaProjects.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AnupamaProjects.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		//initialization code
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".card-body")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	 
	By productsPage = By.cssSelector(".card-body");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector(".toast-message");
	
	

	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsPage);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream().filter(prod -> prod.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);	
		return product;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
	}

}
