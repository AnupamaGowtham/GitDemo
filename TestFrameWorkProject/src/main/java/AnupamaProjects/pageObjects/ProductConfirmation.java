package AnupamaProjects.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AnupamaProjects.AbstractComponents.AbstractComponent;

public class ProductConfirmation extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductConfirmation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement textCountry;
	@FindBy(css=".list-group-item ")
	List<WebElement> displayedCountries;
	@FindBy(css=".action__submit")
	WebElement submit;
	@FindBy(css=".hero-primary")
	WebElement Message;
	
	
	By countries = By.cssSelector(".list-group-item ") ;
	
	
	public String getProductConfirmation(String country) {
		textCountry.sendKeys(country);
		waitForElementToAppear(countries);
		WebElement selectedCountry = displayedCountries.stream().filter(Country -> Country.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		selectedCountry.click();
		submit.click();
		String confirmationMessage = Message.getText();
		return confirmationMessage;
		
	}

}
