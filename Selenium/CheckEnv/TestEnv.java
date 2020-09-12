package CheckEnv;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TestEnv {
	WebDriver driver;
	
  @Test
  public void TC_01() {
	  System.out.print("test");
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
