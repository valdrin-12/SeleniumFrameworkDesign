package Demo.pageobject;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Demo.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = "#toast-container")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public  List<WebElement> getProductList() {
		waitForElementtoBeApear(productsBy);
		return products;

	}

	public WebElement getProductByNames(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		return prod;
	}

	public void addProductToCart(String productName) {

		WebElement prod = getProductByNames(productName);
		prod.findElement(addToCart).click();
		waitForElementtoBeApear(toastMessage);
		waitForElementtoDisapear(spinner);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

	}

}
