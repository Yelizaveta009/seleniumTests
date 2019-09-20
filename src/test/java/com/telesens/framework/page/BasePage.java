package com.telesens.framework.page;

import com.google.common.io.Files;
import com.telesens.framework.test.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class BasePage {
    private static final Logger LOG = LogManager.getLogger( BaseTest.class );

    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this );
    }

    public static BasePage start(WebDriver driver) {
        return new BasePage( driver );
    }

    protected void inputTextField(WebElement textField, String value) {
        textField.click();
        textField.clear();
        textField.sendKeys( value );
    }

    public void assertEqualsLog(Object actual, Object expected) {
        try {
            Assert.assertEquals( actual, expected );
            LOG.info( "Objects are EQUALS." );
        } catch (AssertionError e) {
            LOG.error( "Objects ARE NOT EQUALS "+" Actual: " + actual + " Expected: "+ expected);
            makeScreenshotAssert( driver );
        }
    }

    public boolean assertTrueLog(boolean condition) {
        try {
            Assert.assertTrue( condition );
            LOG.error( " ASSERT RETURN TRUE ");
            return true;
        } catch (AssertionError e) {
            LOG.error( " ASSERT RETURN FALSE", condition );
            makeScreenshotAssert( driver );
            return false;
        }
    }


    private void makeScreenshotAssert(WebDriver driver) {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs( OutputType.FILE );
        String screenName = "screen_" + System.currentTimeMillis() + ".png";
        String screenPath = "D:/temp/assertScreenshots/" + screenName;
        File screen = new File( screenPath );
        try {
            Files.copy( tmp, screen );
        } catch (IOException exc) {
            LOG.error( "Error copying screenshot from '{}' to '{}'. Details: {}",
                    tmp, screen, exc );
        }
    }
}
