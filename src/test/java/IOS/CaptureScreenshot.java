package IOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class CaptureScreenshot {
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
    public void simpleAlert() throws IOException {
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Simple")).click();
        captureScreenshot();
        driver.switchTo().alert().accept();
    }

    @Test
    public void okCancelAlert() throws IOException {
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Okay / Cancel")).click();
        String alertText = driver.switchTo().alert().getText();
        captureScreenshot();
        Assert.assertEquals("A Short Title Is Best\n" +
                "A message should be a short, complete sentence.",alertText);
        driver.switchTo().alert().accept();
    }

    @Test
    public void testEntryAlert() throws IOException {
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Text Entry")).click();
        captureScreenshot();
        driver.switchTo().alert().sendKeys("Accepting Alert");
        driver.switchTo().alert().accept();

    }

    @Test
    public void otherAlert() throws IOException {
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Other")).click();
        captureScreenshot();
        driver.switchTo().alert().dismiss();

    }

    private void captureScreenshot() throws IOException {
        long date = System.currentTimeMillis();
        File screenFile = driver.getScreenshotAs(OutputType.FILE);
        File saveFile = new File("resource/Screenshots/screenshot" + date+".png");
        FileUtils.copyFile(screenFile,saveFile);
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
