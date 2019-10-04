package com.telesens.automationpractice.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class AddressesPage {
    @FindBy(linkText = "Add a new address")
    private SelenideElement addAddress;

    public CreateAddressPage addNewAddress() {
        addAddress.click();
        return page(CreateAddressPage.class);
    }
}
