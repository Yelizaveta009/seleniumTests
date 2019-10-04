package com.telesens.rozetka.ddt.page;

import com.telesens.framework.page.BasePage;
import com.telesens.rozetka.ddt.RozetkaTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShirtsPage extends BasePage {
    private static final Logger LOG = LogManager.getLogger( RozetkaTests.class.getName());

    @FindBy(xpath = "//img[@alt='Orsay']")
    private WebElement orsayButton;

    @FindBy(xpath = "//span[@class='goods-tile__title']//strong")
    private WebElement infoAboutBrand;

    @FindBy(xpath = "//a[@class='catalog-selection__link']")
    private WebElement catalogSelection;

    protected ShirtsPage(WebDriver driver) {
        super( driver );
        this.driver = driver;
    }

    public ShirtsPage filterByOrsay() {
        orsayButton.click();
        return this;
    }

    public ShirtsPage filteringCheckByBrand() {
//        waitingUntilElementToBeClickable( catalogSelection );

        List<String> informationAboutBrand =
           driver.findElements( By.xpath( "//span[@class='goods-tile__title']//strong" ) ).stream()
                   .map( WebElement::getText )
                   .collect( Collectors.toList());

        Set<String> set = new HashSet<>(informationAboutBrand);

        int sizeSet = set.size();

        assertTrueLog( set.contains(catalogSelection.getText()) );
        assertTrueLog( sizeSet == 1 );

        LOG.info( "Sort by brand was successful" );

        return this;
    }
}
