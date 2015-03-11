package de.unikiel.klik;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumGUITest {
	
	//declare a driver to test the grails project in firefox browser
	WebDriver driver = new FirefoxDriver();
	//declare a maximum waiting time for specific elements
	WebDriverWait driverWait = new WebDriverWait(driver, 5);

	@Before
	public void setUp() throws Exception {
		//set the maximum amount of loading time for a website
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		//extend browser window to maximum
		driver.manage().window().maximize();
	}

	@Test
	public void test() {
		
		//open starting page URL
		driver.get("http://localhost:8080/Server/");
		//check if current URL is correct
		String currentUrl = driver.getCurrentUrl();
		String startExpectedUrl = "http://localhost:8080/Server/";
		assert currentUrl.equals(startExpectedUrl);
		
		//perform a login
		//choose the text input field for the user name if it is available on time and type "user"
		WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
		element.sendKeys("user");
		
		//select the input field for the password and put in "password"
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));;
		element.sendKeys("password");
		
		//select and press the login button
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Sign in']")));
		element.click();
		
		//navigate to "Aktivitaeten" page by selecting and pressing the button
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Aktivitaeten")));
		element.click();
		//check if GUI really redirected to "Aktivitaeten"
		currentUrl = driver.getCurrentUrl();
		String activityExpectedUrl = "http://localhost:8080/Server/activity/index";
		assert currentUrl.equals(activityExpectedUrl);
		
		//perform a logout
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Logout']")));
		element.click();
		
		//check if logout worked by checking the current URL
		currentUrl = driver.getCurrentUrl();
		assert currentUrl.equals(startExpectedUrl);
	}
	
	@After
	public void tearDown(){
		driver.close();
	}
}
