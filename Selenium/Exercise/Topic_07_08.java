package Exercise;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08 {
	WebDriver driver;
	By userID = By.name("uid");
	By passwordID = By.name("password");
	By emailID = By.name("emailid");
	By btnSubmit = By.name("btnLogin");
	By linkHere = By.xpath("//a[text()='here']");
	By userIDInfo = By.xpath("//td[text()='User ID :']/following-sibling::td");
	By passwordIDInfo = By.xpath("//td[text()='Password :']/following-sibling::td");
	By btnLogin = By.name("btnLogin");
	By welcomeMessage = By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]");
	By linkNewCustomer = By.linkText("New Customer");
	By linkEditCustomer = By.linkText("Edit Customer");
	By radiobuttonMale = By.xpath("//input[@value='m']");
	By btnSubmitNewCustomer = By.name("sub");
	By linkContinue = By.linkText("Continue");
	String loginUrl = "http://demo.guru99.com/v4/";
	String txtInput = "//td[text()='%s']/following-sibling::td/*";
	String txtGettext = "//td[text()='%s']//following-sibling::td";
	By btnSubmitEditCustomer = By.name("AccSubmit");
	By txtEditCustomer = By.name("cusid");

	String customerName = "Mai Anh Trung";
	String dateOfBirth = "24/07/1988";
	String address = "364 TPHCM";
	String city = "TPHCM";
	String State = "Sai Gon";
	String PIN = "123456";
	String mobileNumber = "0123456789";
	String email = new Random().nextInt(999999) + "anh.maitrung@gmail.com";
	String password = "123456";

	String addressnew = "364 TPHCM CA MAU";
	String citynew = "CA MAU";
	String Statenew = "TINH CA MAU";
	String PINnew = "654321";
	String mobileNumbernew = "9876543210";
	String emailnew = new Random().nextInt(999999) + "anh.maianh@gmail.com";

	@Test
	public void TC_01() {

		// Go to page
		driver.get(loginUrl);
		// Click to click here link
		driver.findElement(linkHere).click();
		// Input email
		driver.findElement(emailID).clear();
		driver.findElement(emailID).sendKeys(email);
		// Click submit button
		driver.findElement(btnSubmit).click();
		// Get username
		String username = driver.findElement(userIDInfo).getText();
		// Get password
		String pass = driver.findElement(passwordIDInfo).getText();
		// Go to log in page
		driver.get(loginUrl);
		// Input username
		driver.findElement(userID).sendKeys(username);
		// Input password
		driver.findElement(passwordID).sendKeys(pass);
		// Click log in button
		driver.findElement(btnLogin).click();
		// Verify home page display
		assertTrue(driver.findElement(welcomeMessage).isDisplayed());
		// Click new customer
		driver.findElement(linkNewCustomer).click();
		// Input customer name
		input("Customer Name", customerName);
		// Click to select gender
		driver.findElement(radiobuttonMale).click();
		// Input Date of Birth
		input("Date of Birth", dateOfBirth);
		// Input address
		input("Address", address);
		// Input city
		input("City", city);
		// Input state
		input("State", State);
		// Input pin
		input("PIN", PIN);
		// Input mobile number
		input("Mobile Number", mobileNumber);
		// Input email
		input("E-mail", email);
		// Input password
		input("Password", password);
		// Click submit button
		driver.findElement(btnSubmitNewCustomer).click();
		// Verify register info
		String CustomerID = getinfo("Customer ID");
		assertEquals(getinfo("Customer Name"), customerName);
		assertEquals(getinfo("Gender"), "male");
		assertEquals(getinfo("Address"), address);
		assertEquals(getinfo("City"), city);
		assertEquals(getinfo("State"), State);
		assertEquals(getinfo("Pin"), PIN);
		assertEquals(getinfo("Mobile No."), mobileNumber);
		assertEquals(getinfo("Email"), email);
		assertEquals(getinfo("Birthdate"),
				dateOfBirth.split("/")[2] + "-" + dateOfBirth.split("/")[1] + "-" + dateOfBirth.split("/")[0]);
		// Click continue button
		driver.findElement(linkContinue).click();
		// Verify home page display
		assertTrue(driver.findElement(welcomeMessage).isDisplayed());
		// Click edit customer linj
		driver.findElement(linkEditCustomer).click();
		// Input customer ID
		driver.findElement(txtEditCustomer).sendKeys(CustomerID);
		// Input submit edit customer button
		driver.findElement(btnSubmitEditCustomer).click();
		// Verify info Customer name and address
		assertEquals(getinfoEdit("Customer Name"), customerName);
		assertEquals(getinfoEdit("Address"), address);
		// Input customer name
		input("Customer Name", customerName);
		// Input Date of Birth
		input("Date of Birth", dateOfBirth);
		// Input address
		input("Address", addressnew);
		// Input city
		input("City", citynew);
		// Input state
		input("State", Statenew);
		// Input pin
		input("PIN", PINnew);
		// Input mobile number
		input("Mobile Number", mobileNumbernew);
		// Input email
		input("E-mail", emailnew);
		// Click submit button
		driver.findElement(btnSubmitNewCustomer).click();
		// Verify edit info
		assertEquals(getinfo("Address"), addressnew);
		assertEquals(getinfo("City"), citynew);
		assertEquals(getinfo("State"), Statenew);
		assertEquals(getinfo("Pin"), PINnew);
		assertEquals(getinfo("Mobile No."), mobileNumbernew);
		assertEquals(getinfo("Email"), emailnew);
		// Click continue button
		driver.findElement(linkContinue).click();
		// Verify home page display
		assertTrue(driver.findElement(welcomeMessage).isDisplayed());

	}

	public String getinfoEdit(String field) {
		WebElement getText = driver.findElement(By.xpath(String.format(txtInput, field)));
		if (getText.isEnabled()) {
			return getText.getText();
		} else {
			return getText.getAttribute("value");
		}
	}

	public String getinfo(String field) {
		WebElement getText = driver.findElement(By.xpath(String.format(txtGettext, field)));
		return getText.getText();
	}

	public void input(String field, String info) {
		WebElement input = driver.findElement(By.xpath(String.format(txtInput, field)));
		if (input.isEnabled()) {
			input.clear();
			input.sendKeys(info);
		} else {
			System.out.print(field + " is disable\n");
		}

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
