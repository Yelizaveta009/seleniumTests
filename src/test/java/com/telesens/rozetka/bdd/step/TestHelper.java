package com.telesens.rozetka.bdd.step;

import com.telesens.framework.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class TestHelper extends BaseTest {

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "d:/selenium/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
        driver.manage().window().maximize();
        return driver;
    }
}
