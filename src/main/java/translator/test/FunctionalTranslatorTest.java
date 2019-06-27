package translator.test;

import com.google.common.io.Resources;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class FunctionalTranslatorTest {

    Map<String, String> dictionary = new HashMap<String, String>();
    String driverPath = Resources.getResource("chromedriver").getPath();
    String htmlPath = Resources.getResource("translate.html").getPath();

    @BeforeMethod
    public void setUp() throws Exception {

        String en2es = Resources.getResource("en_es.csv").getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(en2es))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                dictionary.put(values[0], values[1]);
            }
        }
    }


    @Test(invocationCount = 1, threadPoolSize = 1)
    public void loadTest() throws InterruptedException {
        List<String> keysAsArray = new ArrayList<>(dictionary.keySet());
        Random r = new Random();
        String key = keysAsArray.get(r.nextInt(keysAsArray.size()));
        System.setProperty("webdriver.chrome.driver", driverPath);
        System.out.printf("%n[START] Thread Id : %s is started!",Thread.currentThread().getId());
        ChromeDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.get("file://"+htmlPath);
        driver.findElement(By.id("from")).sendKeys("EN");
        driver.findElement(By.id("to")).sendKeys( "ES");
        driver.findElement(By.id("text")).sendKeys(key);
        driver.findElement(By.id("btnTranslate")).click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("translated")));
            String response = element.getAttribute("value");
            Assert.assertEquals(dictionary.get(key), response);
        }catch (Exception e){
            throw new TimeoutException("TIMEOUT");

        }finally {
            System.out.printf("%n[END] Thread Id : %s",Thread.currentThread().getId());
            driver.quit();
        }
    }
}
