package Exercise;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.crypto.SealedObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Topic_07_08_DropDown {
	WebDriver driver;

	@Test
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
		//Check if select option has 10 values
		assertEquals(dropdown.getOptions().size(), 10);
		//Check if select option 2 is multible selection
		Select dropdown2 = new Select(driver.findElement(selJob2));
		assertTrue(dropdown2.isMultiple());
		

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
