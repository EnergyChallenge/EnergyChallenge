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
	
	//declare a browser driver to test the grails project
	private WebDriver driver;
	//declare a maximum waiting time for specific elements
	private WebDriverWait driverWait;
	//declare a default Webelement to access and use different elements from the website
	private WebElement element;

	
	@Before
	public void setUp() throws Exception {
		
		//initialize the driver for firefox browser
		driver = new FirefoxDriver();
		//specify the maximum amount of time the driver should wait for specific elements
		driverWait = new WebDriverWait(driver, 5);
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
		assert verifyUrl("http://localhost:8080/Server/");
		
		//perform a login with the username user and the password password
		login("user", "password");
		
		//navigate through the website and check if the redirections work
		navigateToPageByLinkText("Aktivitaeten");
		assert verifyUrl("http://localhost:8080/Server/activity/index");
		navigateToPageByLinkText("Rangliste");
		assert verifyUrl("http://localhost:8080/Server/ranking/index");

		logout();
		assert verifyUrl("http://localhost:8080/Server/");
	}
	
	
	//verifying that the current URL is the expected one
	public boolean verifyUrl(String expectedUrl){
		
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.equals(expectedUrl);
	}
	
	
	//perform a login by filling the required text fields and clicking the login button
	public void login(String username, String password) {
		
		//selecting the text field for user name and filling it in
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
		element.sendKeys(username);
		
		//selecting the field for password and filling it in
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
		element.sendKeys(password);
		
		//selecting and pressing the login button
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Sign in']")));
		element.click();
	}
	
	
	//navigate to a subpage within the web application, given by its link text
	public void navigateToPageByLinkText(String linkText){
		
		//get the link element specified by the given link text
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
		element.click();
	}
	
	
	//logout by selecting and clicking the logout button
	public void logout(){
		
		element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Logout']")));
		element.click();
	}
	
	
	@After
	public void tearDown(){
		
		driver.close();
	}
}
