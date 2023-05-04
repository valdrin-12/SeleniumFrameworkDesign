package Demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Demo.pageobject.CartPage;
import Demo.pageobject.CheckoutPage;
import Demo.pageobject.ConfirmationPage;
import Demo.pageobject.LandingPage;
import Demo.pageobject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            LandingPage landingPage = new LandingPage(driver);
            landingPage.goTo();
            ProductCatalogue productCatalogue = landingPage.loginApplication("v1@gmail.com", "1234Qwer.");

            List<WebElement> products = productCatalogue.getProductList();
            productCatalogue.addProductToCart(productName);

            CartPage cartPage = productCatalogue.goToCartPage();
            Boolean match = cartPage.VerifyProductDisplay(productName);
            Assert.assertTrue(match);

            CheckoutPage checkoutPage = cartPage.goToCheckout();
            checkoutPage.selectCountry("India");

            ConfirmationPage confirmationPage = checkoutPage.submitOrder();
            String confirmationMessage = confirmationPage.getConfirmationMessage();
            Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
