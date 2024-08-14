package Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AirPlaneModeTest {

    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        String appiumServerURL = "http://127.0.0.1:4723";
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability("platformName","Android");
        cp.setCapability("appium:automationName","uiautomator2");
        cp.setCapability("appium:appPackage","com.android.settings");
        cp.setCapability("appium:appActivity",".Settings");

        driver = new AndroidDriver(new URL(appiumServerURL),cp);
    }

    @Test
    public void airPlaneModeTest(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Network & internet\")")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.settings:id/switchWidget"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Airplane mode is on\")")));
        String airPlaneModeText = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Airplane mode is on\")")).getText();
        Assert.assertEquals("Airplane mode is on",airPlaneModeText);
    }

    @AfterTest
    public void close(){
        driver.quit();
    }

}
