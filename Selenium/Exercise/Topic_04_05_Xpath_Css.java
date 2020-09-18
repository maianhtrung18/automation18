package Exercise;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_04_05_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() throws InterruptedException {
		// Navigate to index
		driver.get("http://live.demoguru99.com/");
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click My account on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='My Account']")).click();
		// click submit button
		driver.findElement(By.cssSelector("#send2")).click();
		// Verify error message email
		String errorMessageEmail = driver.findElement(By.id("advice-required-entry-email")).getText();
		assertEquals(errorMessageEmail, "This is a required field.");
		// Verify error message pass
		String errorMessagePass = driver.findElement(By.id("advice-required-entry-pass")).getText();
		assertEquals(errorMessagePass, "This is a required field.");

	}

	@Test
	public void TC_02() {
		// Navigate to index
		driver.get("http://live.demoguru99.com/");
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click My account on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='My Account']")).click();
		// fill email
		driver.findElement(By.id("email")).sendKeys("123321321@213213213.321321321");
		// fill email
		driver.findElement(By.id("pass")).sendKeys("123456");
		// click submit button
		driver.findElement(By.cssSelector("#send2")).click();
		// Verify error message email
		String errorMessageEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
		assertEquals(errorMessageEmail, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03() {
		// Navigate to index
		driver.get("http://live.demoguru99.com/");
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click My account on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='My Account']")).click();
		// fill email
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		// fill email
		driver.findElement(By.id("pass")).sendKeys("123");
		// click submit button
		driver.findElement(By.cssSelector("#send2")).click();
		// Verify error message email
		String errorMessagePass = driver.findElement(By.id("advice-validate-password-pass")).getText();
		assertEquals(errorMessagePass, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04() {
		// Navigate to index
		driver.get("http://live.demoguru99.com/");
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click My account on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='My Account']")).click();
		// fill email
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		// fill email
		driver.findElement(By.id("pass")).sendKeys("123123123");
		// click submit button
		driver.findElement(By.cssSelector("#send2")).click();
		// Verify error message
		String errorMessage = driver.findElement(By.cssSelector(".error-msg>ul>li>span")).getText();
		assertEquals(errorMessage, "Invalid login or password.");

	}

	@Test
	public void TC_05() {
		// Navigate to index
		driver.get("http://live.demoguru99.com/");
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click My account on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='My Account']")).click();
		// fill email
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		// fill email
		driver.findElement(By.id("pass")).sendKeys("123123");
		// click submit button
		driver.findElement(By.cssSelector("#send2")).click();
		// Verify dashboard
		String dashboard = driver.findElement(By.cssSelector(".page-title>h1")).getText();
		assertEquals(dashboard, "My Dashboard".toUpperCase());
		// Verify hello
		String hello = driver.findElement(By.cssSelector(".hello>strong")).getText();
		assertEquals(hello, "Hello, Automation Testing!");
		// Verify information
		String[] infomation = driver.findElement(By.xpath("//a[text()='Change Password']/parent::p")).getText().split("\n");
		assertEquals(infomation[0], "Automation Testing");
		assertEquals(infomation[1], "automation_13@gmail.com");

		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click Log out on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='Log Out']")).click();

	}

	@Test
	public void TC_06() {
		String firstName = "Mai";
		String lastName = "Anh";
		String email = new Random().nextInt(9999999) + "Anh@gmail.com";
		String password = "123456";

		// Navigate to index
		driver.get("http://live.demoguru99.com/");
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click My account on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='My Account']")).click();
		// click Create an account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		// fill first name
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		// fill last name
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		// fill email
		driver.findElement(By.id("email_address")).sendKeys(email);
		// fill password
		driver.findElement(By.id("password")).sendKeys(password);
		// fill confirm password
		driver.findElement(By.id("confirmation")).sendKeys(password);
		// click register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		// Verify Thank you message
		String dashboard = driver.findElement(By.cssSelector(".success-msg>ul>li>span")).getText();
		assertEquals(dashboard, "Thank you for registering with Main Website Store.");
		// Verify information
		String[] infomation = driver.findElement(By.xpath("//a[text()='Change Password']/parent::p")).getText().split("\n");
		assertEquals(infomation[0], firstName + " " + lastName);
		assertEquals(infomation[1], email);
		// click account button on menu bar
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		// click Log out on menu side
		driver.findElement(By.xpath("//*[@id='header-account']//a[@title='Log Out']")).click();
		// check if user go to home page
		assertEquals(true, driver.findElement(By.xpath("//*[@id='header']//img[@class='large']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
