package com.telesens.automationpractice;

import com.telesens.automationpractice.page.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class AuthTests {

    private String baseUrl;
    private int timeout;

    @BeforeClass
    public void setUp() {
        baseUrl = "http://automationpractice.com/index.php";
        timeout = 10000;
    }

    @Test(dataProvider = "authDataProvider")
        public void loginTestUsingPages(String login, String password, String username) {
            open(baseUrl, HomePage.class)
                    .clickSignIn()
                    .inputLogin(login)
                    .inputPassword(password)
                    .submit()
                    .isLogIn(username)
                    .logout();
        }

    @DataProvider
    public Object[][] authDataProvider() {
        return new Object[][] {
                {"lizapatology@gmail.com", "password", "Yelizaveta Zhurba"}
        };
    };
}
