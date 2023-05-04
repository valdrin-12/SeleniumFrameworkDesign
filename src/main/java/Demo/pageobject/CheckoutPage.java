package Demo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import Demo.AbstractComponents.AbstractComponents;
//import Demo.pageobject.CartPage;
public class CheckoutPage extends AbstractComponents {
	WebDriver driver;

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(css = "div.details__user > div > div.user__address > div > input")
    WebElement country;

    @FindBy(css= "div.user__address > div > section > button:nth-child(3) > span")
    WebElement selectCountry;

    By results = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName) {
       country.sendKeys("India");
        //Thread.sleep(2000);
        waitForElementtoBeApear(results);
        selectCountry.click();
   }

    
    
    
    
    
    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
