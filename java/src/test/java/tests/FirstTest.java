package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SelectPage;

public class FirstTest {
  private static final Logger logger = LogManager.getLogger(FirstTest.class);

  private static final String SITE_URL = "http://the-internet.herokuapp.com/dropdown";

  private WebDriver driver;

  @BeforeTest
  public void configureWebDriver(){
    // This is web driver manager, install webdriver based on the browser installed on the local machine.
    // More info: https://github.com/bonigarcia/webdrivermanager
    WebDriverManager.chromedriver().setup();
  }

  @BeforeMethod
  public void startWebDriver() {
    driver = new ChromeDriver();
  }

  @Test(description= "Test Selenium's Select function")
  public void selectTest(){
    driver.get(SITE_URL);
    SelectPage selectPage = new SelectPage(driver);
    selectPage.selectByValue("1");
    Assert.assertEquals(selectPage.getCurrentSelectVisibleText(), "Option 1", "Not correct");
  }

  @AfterMethod
  public void tearDown() {
    // terminate webdriver
    driver.quit();
  }
}
