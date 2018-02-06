package com.baijiahulian.tianxiao.auth.sdk.ui.login;

import com.baijiahulian.tianxiao.auth.sdk.TXABaseTest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Cheng on 2018/1/30.
 */
public class TXALoginActivityTest extends TXABaseTest {

    private void pre() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement elAllow = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"允许\")"));
        if (elAllow != null) {
            elAllow.click();
        }

        WebElement elAllow2 = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"允许\")"));
        if (elAllow2 != null) {
            elAllow2.click();
        }

        WebElement el = driver.findElementById(PACKAGE_NAME + ":id/btn_login");
        if (el != null) {
            el.click();
        }

        try {
            WebElement updateEl = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"以后再说\")"));
            if (updateEl != null) {
                updateEl.click();
            }
        } catch (Exception e) {
            // 如果没有找到会抛出异常
            e.printStackTrace();
        }
    }

    @Test
    public void loginSuccTest() throws Exception {
        pre();

        // login 开始
        WebElement mobileElement = driver.findElement(
            MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
        WebElement passwordElement = driver.findElement(
            MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));

        // 使用 xpath 定位，不支持使用 instance 属性定位
        // MobileBy.xpath("//android.widget.EditText[@password='false']")
        // MobileBy.xpath("//android.widget.EditText[@password='true']")
        if (mobileElement != null && passwordElement != null) {
            mobileElement.clear();
            passwordElement.clear();
            mobileElement.sendKeys("13241967851");
            passwordElement.sendKeys("cc123456");
        }

        WebElement loginElement = driver.findElementById(PACKAGE_NAME + ":id/tv_login");
        if (loginElement != null) {
            loginElement.click();
        }

        WebElement campus = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"校区名片\")"));
        assertNotNull(campus);

        String currentPath = System.getProperty("user.dir");
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String fileName = "校区名片_" + (isAndroid ? "android" : "ios") + ".jpg";
        FileUtils.copyFile(srcFile, new File(currentPath, "app/screenshot/" + fileName));
    }

    @Test
    public void loginFailTest() throws Exception {
        pre();

        WebElement mobileElement = driver.findElement(
            MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
        WebElement passwordElement = driver.findElement(
            MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));

        // 使用 xpath 定位，不支持使用 instance 属性定位
        // MobileBy.xpath("//android.widget.EditText[@password='false']")
        // MobileBy.xpath("//android.widget.EditText[@password='true']")
        if (mobileElement != null && passwordElement != null) {
            mobileElement.clear();
            passwordElement.clear();
            mobileElement.sendKeys("13241967851");
            passwordElement.sendKeys("hh123456");
        }

        WebElement loginElement = driver.findElementById(PACKAGE_NAME + ":id/tv_login");
        if (loginElement != null) {
            loginElement.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement failEl =
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='帐号或密码错误，请重新输入']")));
        assertNotNull(failEl);

        String currentPath = System.getProperty("user.dir");
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(currentPath, "app/screenshot/登录失败.jpg"));
    }

    @After
    public void tearDown() throws Exception {
        // driver.quit();
    }
}
