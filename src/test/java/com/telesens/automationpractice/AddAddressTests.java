package com.telesens.automationpractice;

import com.telesens.automationpractice.page.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class AddAddressTests {
    private String baseUrl;
    private int timeout;
    private static String login;
    private static String password;
    private static String address;
    private static String city;
    private static String idStage;
    private static String postCode;
    private static String phone;
    private static String alias;

    @BeforeClass
    public void setUp() {
        baseUrl = "http://automationpractice.com/index.php";
        timeout = 10000;

    }

    @Test(dataProvider = "authDataProvider")
    @Description("Description: test that checks for address creation ")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Супер пупер тест")
    public void loginTestUsingPages(String login, String password, String address, String city,
                                    String idStage, String postCode, String phone, String alias) {

        open(baseUrl, HomePage.class)
                .clickSignIn()
                .inputLogin(login)
                .inputPassword(password)
                .submit()
                .goToMyAdressesPage()
                .addNewAddress()
                .inputAddress(address)
                .inputCity(city)
                .inputIdStage(idStage)
                .inputPostcode(postCode )
                .inputPhone(phone)
                .inputAlias(alias)
                .saveAddress();
    }


    @DataProvider
    public Object[][] authDataProvider() {
        String csvFile = "address.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[]dataCsv = line.split(cvsSplitBy);

                login = dataCsv[0];
                password = dataCsv[1];
                address=dataCsv[2];
                city=dataCsv[3];
                idStage=dataCsv[4];
                postCode=dataCsv[5];
                phone=dataCsv[6];
                alias=dataCsv[7];
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Object[][] {
                {login,password,address,city,idStage,postCode,phone,alias}
        };
    };
}

