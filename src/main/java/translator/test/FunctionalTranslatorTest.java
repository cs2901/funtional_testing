package translator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FunctionalTranslatorTest {
    @Test(invocationCount = 1, threadPoolSize = 1)
    public void loadTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/jesus/Downloads/chromedriver");
        System.out.printf("%n[START] Thread Id : %s is started!",Thread.currentThread().getId());
        ChromeDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.get("file:///Users/jesus/Documents/GitHubEducation/cs2901/funtional_testing/src/main/resources/translate.html");
        driver.findElement(By.id("from")).sendKeys("value", "EN");
        driver.findElement(By.id("to")).sendKeys("value", "ES");
        driver.findElement(By.id("text")).sendKeys("value", "Hello World");
        driver.wait(1000);
        System.out.printf("%n[END] Thread Id : %s",Thread.currentThread().getId());
        driver.quit();

    }
}
