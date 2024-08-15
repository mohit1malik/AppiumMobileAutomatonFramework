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

public class ManageIOSApps {
    public IOSDriver driver;
    private String appPath = System.getProperty("user.dir")+ "/apps/UIKitCatalog.app";

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");

        driver = new IOSDriver(new URL(appiumServerUrl),dc);

    }

    @Test
    public void isAppInstalled(){
        Boolean appCheck = driver.isAppInstalled("com.example.apple-samplecode.UICatalog");
        System.out.println(appCheck);
    }

    @Test
    public void removeAPP(){
        driver.removeApp("com.example.apple-samplecode.UICatalog");
    }

    @Test
    public void installAPP(){
        driver.installApp(appPath);
    }



    @AfterTest
    public void close(){
        driver.quit();
    }
}
