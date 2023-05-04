package Demo.pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Demo.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
	}
	
	public ProductCatalogue loginApplication(String email,String password) {
		driver.findElement(By.cssSelector("#userEmail")).sendKeys(email);
		driver.findElement(By.cssSelector("#userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
