package com.telesens.automationpractice.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.page;

public class CreateAddressPage {


        @FindBy(id = "address1")
        private SelenideElement inputAddress;

        @FindBy(id = "city")
        private SelenideElement inputCity;

        @FindBy(id = "id_state")
        private SelenideElement inputIdState;

        @FindBy(id = "postcode")
        private SelenideElement inputPostcode;

        @FindBy(id = "phone")
        private SelenideElement inputPhone;

        @FindBy(id = "alias")
        private SelenideElement inputAlias;

        @FindBy(id = "submitAddress")
        private SelenideElement submitAddress;


        public CreateAddressPage inputAddress(String address) {
            inputAddress.setValue( address );
            return page(CreateAddressPage.class);
        }

        public CreateAddressPage inputCity(String city) {
            inputCity.setValue(city);
            return page(CreateAddressPage.class);
        }

        public CreateAddressPage inputIdStage(String idStage) {
            inputIdState.click();
            new Select(inputIdState).selectByVisibleText(idStage);
            inputIdState.click();
            return page(CreateAddressPage.class);
        }

        public CreateAddressPage inputPostcode(String postCode) {
            inputPostcode.setValue( postCode );
            return page(CreateAddressPage.class);
        }

        public CreateAddressPage inputPhone(String phone) {
            inputPhone.setValue( phone );
            return page(CreateAddressPage.class);
        }

        public CreateAddressPage inputAlias(String alias) {
            inputAlias.setValue( alias );
            return page(CreateAddressPage.class);
        }

        public AddressesPage saveAddress() {
           submitAddress.click();
            return page(AddressesPage.class);
        }
}

