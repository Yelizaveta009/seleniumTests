package com.telesens.automationpractice.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

//tests

public class AuthPage  {
    @FindBy(id="email")
    private SelenideElement loginField;

    @FindBy(id="passwd")
    private SelenideElement passwordField;

    @FindBy(id="SubmitLogin")
    private SelenideElement submitButton;

    @Step("Input login")
    public AuthPage inputLogin(String login) {
        loginField.setValue(login);
        return page(AuthPage.class);
    }

    @Step("Input password")
    public AuthPage inputPassword(String password) {
        passwordField.setValue(password);
        return page(AuthPage.class);
    }

    @Step("Submit")
    public AccountPage submit() {
        submitButton.click();
        return page(AccountPage.class);
    }
}

