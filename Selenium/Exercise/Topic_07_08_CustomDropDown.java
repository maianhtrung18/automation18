package Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_CustomDropDown {
	WebDriver driver;
	WebDriverWait webWait;
	JavascriptExecutor jsExecutor;

	@Test
	public void TC_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Fast");

	}
	@Test
	public void TC_02() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "12");

	}
	@Test
	public void TC_03() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//div", "Mrs.");

	}


	public String selectDropDown(String parentXpath, String childXpath, String value) {
		driver.findElement(By.xpath(parentXpath)).click();
		webWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childElements = driver.findElements(By.xpath(childXpath));

		for (WebElement childElement : childElements) {

			if (childElement.getText().trim().equals(value)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				childElement.click();

				break;
			}
		}
		return value;
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./BrowserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		webWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
