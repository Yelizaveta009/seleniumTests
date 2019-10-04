package com.telesens.selenide;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTests {

    @Test
    public void firstTest(){
        open("http://automationpractice.com/index.php");
        $(byLinkText("Sign in")).click();
        $(byId("email")).setValue( "lizapatology@gmail.com" );
        $(byId("passwd")).setValue( "dsfsdf" );
        $(byId("SubmitLogin")).submit();

    }
}
