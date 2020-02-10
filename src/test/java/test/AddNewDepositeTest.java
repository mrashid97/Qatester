package test;

//bad one

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewDepositeTest {
	WebDriver driver;
	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=login/");

	}

	@Test
	public void userShouldBeAbleToAddDeposit() throws InterruptedException {

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		

		String expectedTitle ="Dashboard- TechFios Test Application - Billing";
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		 By TRANSCACTIONS_MENU_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Transactions']");
		By NEW_DEPOSIT_PAGE_LOCATOR = By.linkText("New Deposit");

		driver.findElement(TRANSCACTIONS_MENU_LOCATOR).click();
		waitForElement(NEW_DEPOSIT_PAGE_LOCATOR, driver, 30);
		Thread.sleep(2000);
		driver.findElement(NEW_DEPOSIT_PAGE_LOCATOR);
		
		//Select an account 
		Select select = new Select(driver.findElement(By.cssSelector("select#account")));
		select.selectByVisibleText("Swimming");
		Thread.sleep(5000);
		
		Random ran = new Random();
		ran.nextInt(500);
		
	
		String expectedDescription = "AutomationTest" +  new Random().nextInt(500);
		System.out.println("Expected:" + expectedDescription);
		driver.findElement(By.id("description")).sendKeys(expectedDescription);
		driver.findElement(By.id("amount")).sendKeys("2000");
		driver.findElement(By.id("submit")).click();
		
		new WebDriverWait(driver,60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'blockUI')]")));
		waitForElement(By.linkText(expectedDescription),driver, 60);
		Thread.sleep(3000);
		List<WebElement> descriptionElement = driver.findElements(By.xpath("//table/descendant::a"));
		
		Assert.assertTrue(isDescriptionMatch(expectedDescription, descriptionElement), "Deposit unsucessfull!");
		
		Thread.sleep(1000);
	}
	
	

	private boolean isDescriptionMatch(String expectedDescription, List<WebElement> descriptionElement) {
		for(int i=0;i<descriptionElement.size();i++) {
			if(expectedDescription.equalsIgnoreCase(descriptionElement.get(i).getText())) {
				return true;
		}
			  
		}
		return false;
	}

	private void waitForElement(By locator, WebDriver driver1, int time) {
		
		new WebDriverWait(driver1, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}

	@AfterMethod
	public void closeEverything() {

		driver.close();
		driver.quit();

	}
	
	
	

}
