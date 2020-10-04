package Exercise;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Element {

	WebDriver driver;
	By email = By.id("mail");
	By ageUnder18 = By.id("under_18");
	By education = By.id("edu");
	By jobRole01 = By.id("job1");
	By jobRole02 = By.id("job2");
	By interestDevelopmentCheckBox = By.id("development");
	By slider01 = By.id("slider-1");
	By password = By.id("password");
	By ageDisable = By.id("radio-disabled");
	By biography = By.id("bio");
	By jobRole03 = By.id("job3");
	By disableCheckbox = By.id("check-disbaled");
	By slider02 = By.id("slider-2");
	By languageJava = By.id("java");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./BrowserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Check_Element_Display() {
		System.out.print("--------------TC_01--------------");
		
		// Go to page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// check if email is displayed
		assertTrue(isElementDisplayed(email, "Email"));
		// check if Age (under 18) is displayed
		assertTrue(isElementDisplayed(ageUnder18, "Age under 18"));
		// check if education is displayed
		assertTrue(isElementDisplayed(education, "Education"));

	}

	@Test
	public void TC_02_Check_Element_Enable() {
		System.out.print("--------------TC_02--------------");
		// Go to page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// check if email is enable
		assertTrue(isElementEnable(email, "Email"));
		// check if Age (under 18) is enable
		assertTrue(isElementEnable(ageUnder18, "Age under 18"));
		// check if education is enable
		assertTrue(isElementEnable(education, "Education"));
		// check if job role 1 is enable
		assertTrue(isElementEnable(jobRole01, "Job role 1"));
		// check if job role 2 is enable
		assertTrue(isElementEnable(jobRole02, "Job role 2"));
		// check if development check box is enable
		assertTrue(isElementEnable(interestDevelopmentCheckBox, "Development checkbox"));
		// check if slider 1 is enable
		assertTrue(isElementEnable(slider01, "Slider 1"));
		// check if password is enable
		assertFalse(isElementEnable(password, "Password"));
		// check if disable age is enable
		assertFalse(isElementEnable(ageDisable, "Disable age"));
		// check if biography is enable
		assertFalse(isElementEnable(biography, "Biography"));
		// check if job role 3 is enable
		assertFalse(isElementEnable(jobRole03, "Job role 3"));
		// check if disable check box is enable
		assertFalse(isElementEnable(disableCheckbox, "Disable check box"));
		// check if slider 2 is enable
		assertFalse(isElementEnable(slider02, "Slider 2"));

	}

	@Test
	public void TC_03_Check_Element_Select() {
		System.out.print("--------------TC_03--------------");
		// Go to page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Click age under 18
		driver.findElement(ageUnder18).click();
		// Click language java
		driver.findElement(languageJava).click();
		// check if age under 18 is selected
		assertTrue(isElementSelected(ageUnder18, "Age under 18"));
		// check if language java is selected
		assertTrue(isElementSelected(languageJava, "Language Java"));
		// Click language java to not select
		driver.findElement(languageJava).click();
		// check if language java is not selected
		assertFalse(isElementSelected(languageJava, "Language Java"));

	}

	@Test
	public void TC_04_Register_Function() throws InterruptedException {
		System.out.print("--------------TC_04--------------");
		By txtEmail = By.id("email");
		By txtUserName = By.id("new_username");
		By txtPassword = By.id("new_password");
		By checkbox = By.id("marketing_newsletter");
		By signupButton = By.id("create-account");

		String strEmail = "anh.maitrung@gmail.com";
		String strUsername = "maianhtrung";
		List<String> lstPassword = new ArrayList<String>();
		lstPassword.add("123456");
		lstPassword.add("dfggrt");
		lstPassword.add("FDWFVG");
		lstPassword.add("@#$%$#");
		lstPassword.add("hF1#");

		// Go to page
		driver.get("https://login.mailchimp.com/signup/");
		// Fill email
		driver.findElement(txtEmail).sendKeys(strEmail);
		// Fill User name
		driver.findElement(txtUserName).sendKeys(strUsername);

		for (String password : lstPassword) {
			// input password
			driver.findElement(txtPassword).clear();
			driver.findElement(txtPassword).sendKeys(password);
			// check status of sign up password
			assertFalse(isElementEnable(signupButton, "Sign up button of password" + password));

		}
		WebElement checkboxElement = driver.findElement(checkbox);
		// Scroll to check box
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkboxElement);
		// click to check box
		checkboxElement.click();
		// verify check box is selected
		isElementSelected(checkbox, "Check box");

	}

	public boolean isElementSelected(By element, String elementLabel) {
		if (driver.findElement(element).isSelected()) {
			System.out.print(elementLabel + ": Element is selected\n");
			return true;
		} else {
			System.out.print(elementLabel + ": Element is not selected\n");
			return false;
		}

	}

	public boolean isElementDisplayed(By element, String elementLabel) {
		if (driver.findElement(element).isDisplayed()) {
			System.out.print(elementLabel + ": Element is displayed\n");
			return true;
		} else {
			System.out.print(elementLabel + ": Element is not displayed\n");
			return false;
		}

	}

	public boolean isElementEnable(By element, String elementLabel) {
		if (driver.findElement(element).isEnabled()) {
			System.out.print(elementLabel + ": Element is enable\n");
			return true;
		} else {
			System.out.print(elementLabel + ": Element is disable\n");
			return false;
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
