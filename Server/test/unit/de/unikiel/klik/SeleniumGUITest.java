package de.unikiel.klik;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class SeleniumGUITest {
	
	WebDriver driver = new FirefoxDriver();

	@Before
	public void setUp() throws Exception {
		//set the maximum amount of loading time for a website
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		//extend browser window to maximum
		driver.manage().window().maximize();
		//open starting page URL
		driver.get("http://localhost:8080/Server/landing/index");
	}

	@Test
	public void test() {
		
		String currentUrl = driver.getCurrentUrl();
		//check if current URL is correct
		String expectedUrl = "http://localhost:8080/Server/landing/index";
		assert currentUrl.equals(expectedUrl);
		
		//perform a login
		//first input the name
		driver.findElement(By.name("username")).sendKeys("user");
		
		//type the password
		driver.findElement(By.name("password")).sendKeys("password");
		
		//press login button
		driver.findElement(By.linkText("Sign in")).click();
		
		//navigate to "Aktivitaeten" page by pressing the button
		driver.findElement(By.linkText("Aktivitaeten")).click();
		//check if GUI really redirected to "Aktivitaeten"
		currentUrl = driver.getCurrentUrl();
		expectedUrl = "http://localhost:8080/Server/activity/index";
		assert currentUrl.equals(expectedUrl);
	}
	
	@After
	public void tearDown(){
		driver.close();
	}
}
