import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://fakestore.testelka.pl");
        driver.manage().window().fullscreen();
        wait = new WebDriverWait(driver, 10);

    }
    @AfterEach
    public void killBrowser(){
        driver.close();
        driver.quit();
    }

    public WebElement getElementByCss(String locator){
       return driver.findElement(By.cssSelector(locator));
    }
    public void waitForElementToBeVisible(String locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
    }
    public void waitForElementToBeClickable(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
    }
    public void scrollToElement(WebElement elementToScroll){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", elementToScroll);
    }

    public WebElement getElementByCssAndScroll(String locator){
        waitForElementToBeVisible(locator);
        WebElement element = this.getElementByCss(locator);
        scrollToElement(element);
        return element;
    }
}