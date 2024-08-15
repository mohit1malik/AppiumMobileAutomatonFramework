package Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class SendSMS {

    private AndroidDriver driver;
    private static final String appiumServerUrl = "http://127.0.0.1:4723";
    private static final String phoneNo = "555-121-4567";
    private static final String message = "Sample Message";


    @BeforeTest
    public void setup() throws MalformedURLException {


        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:appPackage","com.google.android.apps.messaging");
        dc.setCapability("appium:appActivity",".ui.ConversationListActivity");
        driver = new AndroidDriver(new URL(appiumServerUrl),dc);
    }

    @Test
    public void testMethod(){
        driver.sendSMS(phoneNo,message);
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
