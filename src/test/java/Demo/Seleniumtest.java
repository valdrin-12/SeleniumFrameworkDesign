package Demo;
//import Demo.LandingPage;
import Demo.pageobject.LandingPage;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Seleniumtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage=new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("v1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("1234Qwer.");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));


		List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod=products.stream().filter(product-> product.findElement
			(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));

Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
Assert.assertTrue(match);
driver.findElement(By.cssSelector("div.subtotal.cf.ng-star-inserted > ul > li:nth-child(3) > button")).click();
driver.findElement(By.cssSelector("div.user__address > div > input\n")).sendKeys("ind");
driver.findElement(By.cssSelector("div > section > button:nth-child(3)\n")).click();
driver.findElement(By.cssSelector("div.details__user > div > div.actions > a")).click();
try {
	Thread.sleep(4000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
driver.close();

	}

}
