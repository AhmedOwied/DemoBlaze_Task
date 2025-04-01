package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CartPage {
    private WebDriver driver;
    public P04_CartPage(WebDriver driver) {
        this.driver=driver;
    }

    //locator
    private By cartButton=By.linkText("Cart");
    private By placeOrderButton=By.cssSelector("button[data-toggle='modal']");

    private By nameField= By.id("name");
    private By countryField= By.id("country");
    private By cityField= By.id("city");
    private By creditCardField= By.id("card");
    private By monthField= By.id("month");
    private By yearField= By.id("year");

    private By purchaseButton=By.cssSelector("button[onclick='purchaseOrder()']");

    private By popSuccess=By.cssSelector(".sweet-alert");
    private By messageSuccess=By.cssSelector("div.sweet-alert >h2");

    private By submitButton=By.cssSelector("div.sa-confirm-button-container >button");


    //Method

    public P04_CartPage clickOnCartButton(){
        Waits.clickOnElement(driver,cartButton);
        return this;
    }

    public P04_CartPage clickOnPlaceOrder(){
        Waits.clickOnElement(driver,placeOrderButton);
        return this;
    }

    public P04_CartPage enterName(String name){
        Waits.sendData(driver,nameField,name);
        return this;
    }

    public P04_CartPage enterCountry(String country){
        Waits.sendData(driver,countryField,country);
        return this;
    }

    public P04_CartPage enterCity(String name){
        Waits.sendData(driver,cityField,name);
        return this;
    }

    public P04_CartPage enterCreditCard(String name){
        Waits.sendData(driver,creditCardField,name);
        return this;
    }

    public P04_CartPage enterMonth(String name){
        Waits.sendData(driver,monthField,name);
        return this;
    }

    public P04_CartPage enterYear(String name){
        Waits.sendData(driver,yearField,name);
        return this;
    }

    public P04_CartPage clickOnPurchaseButton(){
        Waits.clickOnElement(driver,purchaseButton);
        return this;
    }

    /*
    public boolean isMessageSuccessfull(){

            // Wait for the success Message to be Present
            Waits.waitForElementPresent(driver,messageSuccess);

            // Get the success message text
            String actualMessage = driver.findElement(messageSuccess).getText();
            System.out.println(actualMessage);

        return true;

    }*/

    public void clickOnSubmitButton(){
        Waits.clickOnElement(driver,submitButton);
    }

}
