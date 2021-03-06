package com.telesens.automationpractice.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class HomePage  {
    @FindBy(linkText = "Sign in")
    private SelenideElement signInLink;

    @Step("Autorizathion")
    public AuthPage clickSignIn() {
        signInLink.click();
        return page(AuthPage.class);
    }
}


