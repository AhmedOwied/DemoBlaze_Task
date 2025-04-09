package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_ProductItemPage {
    private WebDriver driver;
    public P03_ProductItemPage(WebDriver driver) {
        this.driver=driver;
    }

    //locators
    private By cartButton= By.partialLinkText("Add to cart");
    //methods

    public P03_ProductItemPage clickOnCartButton(){
        Waits.clickOnElement(driver,cartButton);
        return this;
    }

    public void waitForAlertPresent(){
        Waits.acceptAlert(driver);
    }

}
