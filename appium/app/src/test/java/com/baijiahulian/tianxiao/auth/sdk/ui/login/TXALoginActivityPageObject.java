package com.baijiahulian.tianxiao.auth.sdk.ui.login;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * TODO: 类的一句话描述
 * <p>
 * TODO: 类的功能和使用详细描述
 * <p>
 * Created by Cheng on 2018/1/31.
 */
public class TXALoginActivityPageObject {

    // 这里apk用netschool主工程作为主体测试
    public static final String PACKAGE_NAME = "com.baijiahulian.tianxiao.netschool";

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"天校\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    public WebElement mobileElement;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"天校\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSecureTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    public WebElement passwordElement;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"登录\"]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"" + PACKAGE_NAME + ":id/tv_login\")")
    public WebElement loginElement;

    @iOSFindBy(accessibility = "校区名片")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"校区名片\")")
    public WebElement campusTitleElement;

    @iOSFindBy(uiAutomator = ".elements()[0]")
    @AndroidFindBy(xpath = "//*[@text='帐号或密码错误，请重新输入']")
    public WebElement failToastElement;

}
