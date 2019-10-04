package com.telesens.rozetka.bdd.step;

import com.telesens.rozetka.ddt.page.MainPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class RozetkaSteps {

    private String baseUrl = "https://rozetka.com.ua/";
    private TestHelper testHelper;
    private WebDriver driver;


    @Before
    public void setUp() {
        testHelper = new TestHelper();
        driver = testHelper.getDriver();
    }

    @Given("Я нахожусь на домашней странице")
    public void IAmOnHomePage() {
        driver.get(baseUrl);
    }

    @Then("я выбираю {string}")
    public void selectSignIn(String signIn) {
        new MainPage( driver ).clickSignIn();
    }
    @And("ввожу валидный email в поле {string}")
    public void inputNumber(String login) {
        new MainPage( driver ).inputLogin("lizapatology@gmail.com");
    }
    @Then("ввожу валидный пароль в поле {string}")
    public void inputPassword(String password) {
        new MainPage( driver ).inputPassword("1Q2W3E4R");
    }
    @And("я нажимаю войти")
    public void pressSubmit() {
        new MainPage( driver ).pressSubmit();
    }
    @Then("я выхожу из личного кабинета")
    public void pressSignOut() {
        new MainPage( driver ).signOut();
    }


    @After
    public void tearDown() {
       driver.quit();
    }
}
