package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestngLoging {
	WebDriver driver;
	@Test
	public  void openTechfiosSite() throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=login/");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("techfiosdemo@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		
		String expectedTitle ="Dashboard- TechFios Test Application - Billing";
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		By TRANSCACTIONS_MENU_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Transactions']");
		By NEW_DEPOSIT_PAGE_LOCATOR = By.linkText("New Deposit");
		driver.findElement(TRANSCACTIONS_MENU_LOCATOR).click();
		waitForElement(NEW_DEPOSIT_PAGE_LOCATOR,driver,30);
		driver.findElement(NEW_DEPOSIT_PAGE_LOCATOR).click();
		
		Select select = new Select(driver.findElement(By.cssSelector("select#account")));
		select.selectByVisibleText("Mike99");
		String expectedDescription = "Automation" + new Random().nextInt(999);
		System.out.println("Expected: " + expectedDescription);
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys(expectedDescription);
		driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("10000");
		driver.findElement(By.cssSelector("button#submit")).click();
		
		
		
		Thread.sleep(5000);
		//new WebDriverWait(driver,60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")))
		
			
		
	}
	private void waitForElement(By locator, WebDriver driver1, int time) {
	
		new WebDriverWait(driver1, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));	
		
	}

}
