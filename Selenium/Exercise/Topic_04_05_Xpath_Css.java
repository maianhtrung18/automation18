package Exercise;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

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
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
		// click My account on menu side
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
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
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
		// click My account on menu side
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
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
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
		// click My account on menu side
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
