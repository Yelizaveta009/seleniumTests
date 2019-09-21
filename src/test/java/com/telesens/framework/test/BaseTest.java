package com.telesens.framework.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final Logger LOG = LogManager.getLogger( BaseTest.class );
    protected EventFiringWebDriver driver;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws Exception {
        System.setProperty( "webdriver.chrome.driver", "d:/selenium/chromedriver.exe" );
        System.setProperty( "webdriver.gecko.driver", "d:/selenium/geckodriver.exe" );
//        driver = new ChromeDriver();
        driver = new EventFiringWebDriver( new ChromeDriver() );
        driver.register( new WebDriverEventListenerImpl() );
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
        driver.manage().window().maximize();
    }

    @BeforeMethod (alwaysRun = true)
    public void logTestStart(Method method) {

        LOG.info( "Start test {}", method.getName() );
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {

        LOG.info( "Finish test {}", method.getName() );
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}

