package IOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSAlerts {
    public IOSDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/UIKitCatalog.app");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");

        driver = new IOSDriver(new URL(appiumServerUrl),dc);

    }

    @Test
    public void simpleAlert(){
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Simple")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void okCancelAlert(){
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Okay / Cancel")).click();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals("A message should be a short, complete sentence.",alertText);
        driver.switchTo().alert().accept();
    }

    @Test
    public void testEntryAlert(){
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Text Entry")).click();
        driver.switchTo().alert().sendKeys("Accepting Alert");
        driver.switchTo().alert().accept();

    }

    @Test
    public void otherAlert(){
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Other")).click();
        driver.switchTo().alert().dismiss();

    }


    @AfterTest
    public void close(){
        driver.quit();
    }
}
