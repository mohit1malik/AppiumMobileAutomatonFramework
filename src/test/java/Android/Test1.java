package Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1{

    //    AndroidDriver driver;
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();

    }

    @Test
    public void Test() throws InterruptedException {
        driver.get("https://www.google.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
