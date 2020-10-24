package Exercise;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_07_08_CustomDropDown {
	WebDriver driver;
	WebDriverWait webWait;
	JavascriptExecutor jsExecutor;

	@Test
	public void TC_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Fast");
		assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Fast");

	}
	@Test
	public void TC_02() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");

	}
	@Test
	public void TC_03() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//div", "Mrs.");
		assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Mrs.");

	}
	@Test
	public void TC_04() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDown("//span[@id='files-button']", "//ul[@id='files-menu']//div", "ui.jQuery.js");
		assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "ui.jQuery.js");

	}
	
	@Test
	public void TC_05() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectDropDown("//div[@id='root']", "//div[@id='root']//span", "Justen Kitsune");
		assertEquals(driver.findElement(By.xpath("//div[@id='root']//div[@class='divider text']")).getText().trim(), "Justen Kitsune");

	}
	
	@Test
	public void TC_06() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectDropDown("//div[@id='app']/div[@class='btn-group']", "//div[@id='app']//ul//a", "Second Option");
		assertTrue(driver.findElement(By.xpath("//div[@id='app']/div[@class='btn-group']/li")).getText().trim().equals("Second Option"));

	}
	
	@Test
	public void TC_07() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']//li", "Tennis");
		assertEquals(getHiddenText("select[id='games_hidden']>option"), "Tennis");
	}
	
	@Test
	public void TC_08() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		selectDropDown("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Nissan");
		assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"), "Nissan");

		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		delaySecond(1);
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.DELETE);
		
		selectDropDown("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Volvo");
		assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"), "Volvo");
	}
	
	public String getHiddenText(String cssLocator) {
		
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
	}

	public void delaySecond(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		driver.quit();
	}

}
