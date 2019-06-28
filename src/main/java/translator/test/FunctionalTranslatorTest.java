package translator.test;

import com.google.common.io.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FunctionalTranslatorTest {

    Map<String, String> dicEn2Es = new HashMap<String, String>();
    Map<String, String> dicEs2En = new HashMap<String, String>();
    String driverPath = Resources.getResource("chromedriver").getPath();
    String htmlPath = Resources.getResource("translate.html").getPath();

    @BeforeMethod
    public void setUp() throws Exception {
        String en2es = Resources.getResource("en_es.csv").getPath();
        String es2en = Resources.getResource("es_en.csv").getPath();
        loadDictionary(dicEn2Es, en2es);
        loadDictionary(dicEs2En, es2en);
    }

    @Test(invocationCount = 5, threadPoolSize = 5)
    public void loadTestSpanishEnglish() throws InterruptedException {
        loadTesting(dicEs2En, "SP", "EN");
    }

    @Test(invocationCount = 5, threadPoolSize = 5)
    public void loadTestEnglishSpanish() throws InterruptedException {
        loadTesting(dicEn2Es, "EN", "SP");
    }


    private void loadTesting(Map<String,String> map, String from, String to) throws InterruptedException {
        List<String> keysAsArray = new ArrayList<>(map.keySet());
        Random r = new Random();
        String key = keysAsArray.get(r.nextInt(keysAsArray.size()));
        System.setProperty("webdriver.chrome.driver", driverPath);
        System.out.printf("%n[START] Thread Id : %s is started!",Thread.currentThread().getId());
        ChromeDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.get("file://"+htmlPath);
        driver.findElement(By.id("from")).sendKeys(from);
        driver.findElement(By.id("to")).sendKeys( to);
        driver.findElement(By.id("text")).sendKeys(key);
        driver.findElement(By.id("btnTranslate")).click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("translated")));
            String response = element.getAttribute("value");
            Assert.assertEquals(map.get(key).toLowerCase(), response.toLowerCase());
        }catch (Exception e){
            throw new TimeoutException("TIMEOUT");

        }finally {
            System.out.printf("%n[END] Thread Id : %s",Thread.currentThread().getId());
            driver.quit();
        }
    }


    private void loadDictionary(Map map, String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                map.put(values[0], values[1]);
            }
        }
    }
}
