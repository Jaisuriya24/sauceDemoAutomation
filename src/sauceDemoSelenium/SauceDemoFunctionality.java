package sauceDemoSelenium;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;  

public class SauceDemoFunctionality {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.driver.chromedriver",
				"C://Users//hp//Downloads//SeleniumHandsons//SeleniumFirstProject//chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-save-password-bubble");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		// Login to Application
		WebElement userName = driver.findElement(By.id("user-name"));
		userName.sendKeys("standard_user");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		loginBtn.click();
		Thread.sleep(2000);

		// Alert handling
		WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			Alert alert = waitAlert.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (Exception e) {
			System.out.println("No alert present");
		}

		// Add product to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addToCart = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
		Thread.sleep(6000);
		addToCart.click();
		Thread.sleep(2000);

		// verify product added in cart
		WebElement CartBtn = driver.findElement(By.className("shopping_cart_link"));
		CartBtn.click();
		String productName = "Sauce Labs Backpack";
		List<WebElement> cartProduct = driver.findElements(By.xpath("//div[contains(text(),'" + productName + "')]"));
		Thread.sleep(2000);
		if (!cartProduct.isEmpty()) {
			System.out.println("Product exists");

		} else {
			System.out.println("Product does not exists");
		}
		Thread.sleep(2000);
		WebElement continueShopping = driver.findElement(By.id("continue-shopping"));
		continueShopping.click();
		Thread.sleep(2000);

		// verify product removed from cart
		WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
		removeBtn.click();
		Thread.sleep(6000);
		WebElement CartBtn2 = driver.findElement(By.className("shopping_cart_link"));
		CartBtn2.click();
		if (cartProduct.isEmpty()) {
			System.out.println("Product successfully removed");
		} else {
			System.out.println("Product not removed from cart");
		}
		driver.quit();

	}

}
