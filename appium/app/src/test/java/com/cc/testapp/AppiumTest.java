package com.cc.testapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import static org.junit.Assert.assertEquals;

/**
 * TODO: 类的一句话描述
 * <p>
 * TODO: 类的功能和使用详细描述
 * <p>
 * Created by Cheng on 2018/1/30.
 */
public class AppiumTest {

    public AppiumDriver<WebElement> driver;

    private static final String url = "http://127.0.0.1:4723/wd/hub";
    private static final String APP = "com.cc.testapp:";

    @Test
    public void apiDemo() throws Exception {
        // driver.findElement(MobileBy.AndroidUIAutomator("Hello World!")).click();
        // WebElement el = driver.findElementByClassName("android.widget.TextView");
        WebElement el = driver.findElementById("com.cc.testapp:id/tv_hello");
        assertEquals("Hello World!", el.getText());
        el.click();
    }

    @Before
    public void setUp() throws Exception {
        // AppiumServiceBuilder builder =
        // new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
        // .withIPAddress("127.0.0.1").usingPort(4723);
        // AppiumDriverLocalService driverLocalService = builder.build();
        // driverLocalService.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.cc.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "app/");
        File app = new File(appDir, "app-debug.apk");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<>(new URL(url), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        // driver.quit();
    }
}
