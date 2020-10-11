package Exercise;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_DropDown {
	WebDriver driver;

//	@Test
	public void TC_01() throws InterruptedException {
		By selJob1 = By.id("job1");
		By selJob2 = By.id("job2");
		// Go to web site
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Verify select is not multiple select
		Select dropdown = new Select(driver.findElement(selJob1));
		assertFalse(dropdown.isMultiple());
		// Select Mobile testing
		dropdown.selectByVisibleText("Mobile Testing");
		// Check if user can select successfully
		assertEquals(dropdown.getFirstSelectedOption().getText(), "Mobile Testing");
		// Select Manual testing
		dropdown.selectByValue("manual");
		// Check if user can select successfully
		assertEquals(dropdown.getFirstSelectedOption().getAttribute("value"), "manual");
		// Select Manual testing
		dropdown.selectByIndex(9);
		// Check if user can select successfully
		assertEquals(dropdown.getFirstSelectedOption().getAttribute("value"), "function");
		// Check if select option has 10 values
		assertEquals(dropdown.getOptions().size(), 10);
		// Check if select option 2 is multible selection
		Select dropdown2 = new Select(driver.findElement(selJob2));
		assertTrue(dropdown2.isMultiple());
		//Select value
		dropdown2.selectByVisibleText("Automation");
		dropdown2.selectByVisibleText("Mobile");
		dropdown2.selectByVisibleText("Desktop");
		//Check if the selected values are selected
		List<WebElement> selectedOptions = dropdown2.getAllSelectedOptions();
		assertEquals(selectedOptions.get(0).getText(), "Automation");
		assertEquals(selectedOptions.get(1).getText(), "Mobile");
		assertEquals(selectedOptions.get(2).getText(), "Desktop");
		//De-select All
		dropdown2.deselectAll();
		//check if dropdown2 is de-selected all
		assertEquals(dropdown2.getAllSelectedOptions().size(), 0);
	
	}
	
	@Test
	public void TC_02() throws InterruptedException {
		String email = new Random().nextInt(9999999) + "anh.maitrung@gmail.com";
		String password = "123456";
		String firstname = "Mai Anh";
		String lastname = "Trung";
		String selectday = "1";
		String selectmonth = "May";
		String selectyear = "1980";

		// Goto page
		driver.get("https://demo.nopcommerce.com");
		// Navigate to register page
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		//Select gender
		driver.findElement(By.id("gender-male")).click();
		//Input first name
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		//Input last name
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		// Select birthday
		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		day.selectByVisibleText(selectday);
		Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		month.selectByVisibleText(selectmonth);
		Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		year.selectByVisibleText(selectyear);
		//Input email
		driver.findElement(By.id("Email")).sendKeys(email);
		//Input password
		driver.findElement(By.id("Password")).sendKeys(password);
		//Input confirm password
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		//Click to register button
		driver.findElement(By.id("register-button")).click();
		//Verify user register successfully
		assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		//Click to My account
		driver.findElement(By.linkText("My account")).click();
		//Verify register information
		day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstname);
		assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastname);
		assertEquals(day.getFirstSelectedOption().getText(), selectday);
		assertEquals(month.getFirstSelectedOption().getText(), selectmonth);
		assertEquals(year.getFirstSelectedOption().getText(), selectyear);
		assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./BrowserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
