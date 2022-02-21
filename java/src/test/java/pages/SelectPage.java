package pages;
import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class SelectPage {
  private static final Logger logger = LogManager.getLogger(SelectPage.class);

  private WebDriver driver;

  @FindBy(id="dropdown")
  private WebElement selectElement;

  public SelectPage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public SelectPage selectByValue(String value){
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(30L))
        .pollingEvery(Duration.ofSeconds(1L))
        .ignoring(NoSuchElementException.class);

    wait.until(ExpectedConditions.visibilityOf(selectElement));
    Select selector = new Select(selectElement);
    selector.selectByValue(value);
    return this;
  }

  public String getCurrentSelectVisibleText(){
    Select selector = new Select(selectElement);
    return selector.getFirstSelectedOption().getText();
  }

}
