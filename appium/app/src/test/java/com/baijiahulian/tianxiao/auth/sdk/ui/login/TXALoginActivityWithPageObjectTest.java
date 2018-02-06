package com.baijiahulian.tianxiao.auth.sdk.ui.login;

import com.baijiahulian.tianxiao.auth.sdk.TXABaseTest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Cheng on 2018/1/30.
 */
public class TXALoginActivityWithPageObjectTest extends TXABaseTest {

    private TXALoginActivityPageObject pageObject;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        pageObject = new TXALoginActivityPageObject();
        PageFactory.initElements(new AppiumFieldDecorator(driver), pageObject);
    }

    private void pre() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if (isAndroid) {
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
                WebElement updateEl =
                    driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"以后再说\")"));
                if (updateEl != null) {
                    updateEl.click();
                }
            } catch (Exception e) {
                // 如果没有找到会抛出异常
                e.printStackTrace();
            }
        } else {
            MobileElement notAllowBtn = (MobileElement) driver.findElementByAccessibilityId("Don’t Allow");
            notAllowBtn.click();

            new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("txa login guide btn")))
                .click();
        }
    }

    @Test
    public void loginSuccTest() throws Exception {
        pre();

        // login 开始
        if (pageObject.mobileElement != null && pageObject.passwordElement != null) {
            pageObject.mobileElement.clear();
            pageObject.passwordElement.clear();
            pageObject.mobileElement.sendKeys("13241967851");
            pageObject.passwordElement.sendKeys("cc123456");
        }

        if (pageObject.loginElement != null) {
            pageObject.loginElement.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement campus = wait.until(ExpectedConditions.visibilityOf(pageObject.campusTitleElement));
        assertNotNull(campus);

        String currentPath = System.getProperty("user.dir");
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String fileName = "校区名片_" + (isAndroid ? "android" : "ios") + ".jpg";
        FileUtils.copyFile(srcFile, new File(currentPath, "app/screenshot/" + fileName));
    }

    @Test
    public void loginFailTest() throws Exception {
        pre();

        // login 开始
        if (pageObject.mobileElement != null && pageObject.passwordElement != null) {
            pageObject.mobileElement.clear();
            pageObject.passwordElement.clear();
            pageObject.mobileElement.sendKeys("13241967851");
            pageObject.passwordElement.sendKeys("hh123456");
        }

        if (pageObject.loginElement != null) {
            pageObject.loginElement.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement failEl = wait.until(new ExpectedCondition<WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver input) {
                return pageObject.failToastElement;
            }
        });
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
