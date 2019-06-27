package translator.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FunctionalTranslatorTest {
    @Test(invocationCount = 10, threadPoolSize = 5)
    public void loadTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/jesus/Downloads/chromedriver");
        System.out.printf("%n[START] Thread Id : %s is started!",Thread.currentThread().getId());
        ChromeDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.get("http://localhost:8080/translator/EN/ES/HELLO");
        System.out.printf("%n[END] Thread Id : %s",Thread.currentThread().getId());
        driver.quit();

    }
}
