package com.telesens.automationpractice;

import com.telesens.automationpractice.page.AuthPage;
import com.telesens.framework.page.BasePage;
import com.telesens.framework.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.util.Properties;

import static com.telesens.automationpractice.page.HomePage.startFromHome;

public class AuthTests extends BaseTest {
    private static final String DEFAULT_PATH = "src/main/resources/automationpractice.properties";
    private String baseUrl;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        String automationPracticePath = System.getProperty( "automationpractice" );
        if (automationPracticePath == null)
            automationPracticePath = DEFAULT_PATH;

        Properties prop = new Properties();
        prop.load( new FileReader( automationPracticePath ) );
        baseUrl = prop.getProperty( "base.url" );

    }

    @Test(dataProvider = "authErrorMessageProvider")
    public void testAuthErrorMessage(String login, String passw, String expectedError){
        BasePage basePage = startFromHome( driver, baseUrl )
                .clickSignIn()
                .enterEmail(login)
                .enterPassword(passw)
                .pressSubmit();

        String actualError = ((AuthPage) basePage).getErrorMessage();
        ((AuthPage) basePage).checkingAssertEquals(actualError,expectedError);
    }

    @DataProvider(name="authErrorMessageProvider")
    public Object[][] authErrorMessageProvider() {
        return new Object[][]{
                {"log", "passw", "Invalid email address./"},
                {"vasya", "123", "Invalid email address."}
        };
    }
}
