package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(xpath = "//a[@class='header-topline__user-link link-dashed']")
    private WebElement enterLink;

    @FindBy(xpath = "//input[@id='auth_email']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='auth_pass']")
    private WebElement passwordField;

    @FindBy(xpath = "//span[@class='button-inner']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@class='header-topline__user-link link-dashed']")
    private WebElement userName;

    @FindBy(partialLinkText = "Выход")
    private WebElement exitButton;

    @FindBy(xpath = "//ul[@class='menu-categories menu-categories_type_main']//li[1]")
    private WebElement laptopsAndTechnology;

    @FindBy(xpath = "//ul[@class='menu-categories menu-categories_type_main']//li[9]")
    private WebElement clothesFootwearDecoration;

    @FindBy(partialLinkText = "Мониторы")
    private WebElement monitors;

    @FindBy(partialLinkText = "Блузки")
    private WebElement shirts;

    @FindBy(xpath = "//p[@class='error-message_color_red']")
    private WebElement errorMessageWrongPassword;

    @FindBy(xpath = "//span[@class='auth-modal__form-email']")
    private WebElement errorMessageWrongLogin;

    @FindBy(css = "body > div > div > div:nth-child(3) > div > button > span")
    private WebElement buttonToCollapse;


    public MainPage(WebDriver driver) {
        super( driver );
        this.driver = driver;
    }

    public static MainPage startFromMain(WebDriver driver, String baseUrl) {
        driver.get( baseUrl );
        return new MainPage( driver );
    }

    public MainPage clickSignIn() {
        enterLink.click();
        return this;
    }

    public MainPage inputLogin(String login) {
        inputTextField( loginField, login );
        return this;
    }

    public MainPage inputPassword(String password) {
        inputTextField( passwordField, password );
        return this;
    }

    public MainPage pressSubmit() {
        loginButton.click();
        return this;
    }

    public MainPage signOut() {
        pointAndClick( userName,exitButton );
        return this;
    }

    public MonitorsPage goToMonitorsPage() {
        pointAndClick(laptopsAndTechnology,monitors );

        return new MonitorsPage( driver );
    }

    public ShirtsPage goToShirtsPage() {
        scrollDownAbit();

        pointAndClick(clothesFootwearDecoration,shirts);

        return new ShirtsPage( driver );
    }
}



