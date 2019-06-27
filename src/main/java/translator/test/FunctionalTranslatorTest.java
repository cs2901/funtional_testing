package translator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FunctionalTranslatorTest {
    @Test(invocationCount = 100, threadPoolSize = 5)
    public void loadTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/jesus/Downloads/chromedriver");
        System.out.printf("%n[START] Thread Id : %s is started!",Thread.currentThread().getId());
        ChromeDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.get("file:///Users/jesus/Documents/GitHubEducation/cs2901/funtional_testing/src/main/resources/translate.html");
        driver.findElement(By.id("from")).sendKeys("EN");
        driver.findElement(By.id("to")).sendKeys( "ES");
        driver.findElement(By.id("text")).sendKeys("Hello World");
        driver.findElement(By.id("btnTranslate")).click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("translated")));
            String response = element.getAttribute("value");
            Assert.assertEquals("Hola Mundo", response);
        }catch (Exception e){
            throw new TimeoutException("TIMEOUT");

        }finally {
            System.out.printf("%n[END] Thread Id : %s",Thread.currentThread().getId());
            driver.quit();
        }
    }
}
