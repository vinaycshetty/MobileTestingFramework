package com.vs;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Created by : Vinay Shetty
 * on 10-03-2025 at 00:24
 **/
public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void setup() throws MalformedURLException {
        // Android driver
        // Appium code -> appium server -> mobile
        // to start appium programmatically
        // file path
//        File file = new File("C://Users//vinay//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
//         service = new AppiumServiceBuilder().withAppiumJS(file).withIPAddress("127.0.0.1").usingPort(4723).build();
//        service.start();
        // two parameters to pass is one URL to appium server and options
        URL url = new URL("http://127.0.0.1:4723");
        // options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("VinayMobile");
        options.setApp("C://repo//MobileTestingFramework//src//test//resources//ApiDemos-debug.apk");
        driver = new AndroidDriver(url, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void LongPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
    }

    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"
        ));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
//        service.stop();
    }
}
