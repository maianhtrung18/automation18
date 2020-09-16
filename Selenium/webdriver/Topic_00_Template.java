package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_00_Template {
	WebDriver driver;
	
  @Test
  public void TC_01() {
	  System.out.print("test");
	  By ghu = By.xpath("");
  }
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http:/facebook.com");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
