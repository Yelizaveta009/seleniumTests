package com.telesens.rozetka;

import com.telesens.framework.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.telesens.rozetka.page.MainPage.startFromMain;

public class RozetkaTests extends BaseTest {
    private static final String DEFAULT_PATH = "src/main/resources/rozetka.properties";
    private String baseUrl;
    private String login;
    private String password;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        String rozetkaPath = System.getProperty( "rozetka" );
        if (rozetkaPath == null)
            rozetkaPath = DEFAULT_PATH;

        Properties prop = new Properties();
        prop.load( new FileReader( rozetkaPath ) );

        baseUrl = prop.getProperty("baseUrl");
        login = prop.getProperty("login");
        password = prop.getProperty("password");
    }


    @Test(dataProvider = "loginProvider")
    public void testLogin(String login,String password) {
        startFromMain( driver, baseUrl )
                .clickSignIn()
                .inputLogin(login)
                .inputPassword(password)
                .pressSubmit()
                .signOut();
    }

    @Test
    public void testSort() {
        startFromMain(driver, baseUrl)
                .goToMonitorsPage()
                .sortedMonitorsAscending()
                .sortingCheck();
    }

    @Test
    public void testFilter() {
        startFromMain( driver, baseUrl )
                .goToMonitorsPage()
                .filterMonitorsByPrice()
                .filteringCheck();
    }
        @DataProvider(name = "loginProvider")
        public Object[][] loginProvider() {
            return new Object[][]{
                    {login,password},
            };

    }
}