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
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		//extend browser window to maximum
		driver.manage().window().maximize();
		//open starting page URL
		driver.get("http://localhost:8080/Server/");
	}

	@Test
	public void test() {
		
		//check if current URL is correct
		String currentUrl = driver.getCurrentUrl();
		String startExpectedUrl = "http://localhost:8080/Server/";
		assert currentUrl.equals(startExpectedUrl);
		
		//perform a login
		//first input the name
		driver.findElement(By.name("username")).sendKeys("user");
		
		//type the password
		driver.findElement(By.name("password")).sendKeys("password");
		
		//press login button
		driver.findElement(By.cssSelector("input[value='Sign in']")).click();
		
		//navigate to "Aktivitaeten" page by pressing the button
		driver.findElement(By.linkText("Aktivitaeten")).click();
		//check if GUI really redirected to "Aktivitaeten"
		currentUrl = driver.getCurrentUrl();
		String activityExpectedUrl = "http://localhost:8080/Server/activity/index";
		assert currentUrl.equals(activityExpectedUrl);
		
		//perform a logout
		driver.findElement(By.name("logout")).submit();
		currentUrl = driver.getCurrentUrl();
		assert currentUrl.equals(startExpectedUrl);
	}
	
	@After
	public void tearDown(){
		driver.close();
	}
}
