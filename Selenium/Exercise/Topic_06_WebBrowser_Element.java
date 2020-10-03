package Exercise;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowser_Element {

	WebDriver driver;
	String url = "http://live.demoguru99.com";
	By myAccountLink = By.xpath("//div[@class='footer']//a[text()='My Account']");
	By btnCreateAnAccount = By.xpath("//a[@title='Create an Account']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./BrowserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Verify_Url() {
		// Go to the page
		driver.get(url);
		// Click my account link
		driver.findElement(myAccountLink).click();
		// Verify login page url
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		// Click create my account button
		driver.findElement(btnCreateAnAccount).click();
		// Verify register page url
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		// Go to the page
		driver.get(url);
		// Click my account link
		driver.findElement(myAccountLink).click();
		// Verify login page title
		assertEquals(driver.getTitle(), "Customer Login");
		// Click create my account button
		driver.findElement(btnCreateAnAccount).click();
		// Verify register page title
		assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Naviage_Function() {
		// Go to the page
		driver.get(url);
		// Click my account link
		driver.findElement(myAccountLink).click();
		// Click create my account button
		driver.findElement(btnCreateAnAccount).click();
		// Verify register page url
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		// Back to login page
		driver.navigate().back();
		// Verify login page url
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		// forward to register page
		driver.navigate().forward();
		// Verify register page url
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_04_Get_Page_Source_Code() {
		// Go to the page
		driver.get(url);
		// Click my account link
		driver.findElement(myAccountLink).click();
		// Verify Login page contain "Log in or Create an Account"
		assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		// Click create my account button
		driver.findElement(btnCreateAnAccount).click();
		// Verify Login page contain "Log in or Create an Account"
		assertTrue(driver.getPageSource().contains("Create an Account"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
