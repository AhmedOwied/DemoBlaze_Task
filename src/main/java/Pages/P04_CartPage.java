package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.Enventhandler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class P04_CartPage {
    private WebDriver driver;
    public P04_CartPage(WebDriver driver) {
        this.driver=driver;
    }

    //locators
    private By cartButton=By.linkText("Cart");
    private By placeOrderButton=By.cssSelector("button[data-toggle='modal']");

    private By nameField= By.id("name");
    private By countryField= By.id("country");
    private By cityField= By.id("city");
    private By creditCardField= By.id("card");
    private By monthField= By.id("month");
    private By yearField= By.id("year");

    private By purchaseButton=By.cssSelector("button[onclick='purchaseOrder()']");

    private By popAlert=By.className("sweet-alert");
    private By messageSuccess=By.cssSelector("div.sweet-alert >h2");

    private By markIcon=By.className("animateSuccessLong");
    private By submitButton=By.cssSelector("div.sa-confirm-button-container >button");

    private By totalPrice = By.xpath("//h3[@id='totalp']");
    private By priceofDeletedElement=By.xpath("//td[text()='Samsung galaxy s6']/following-sibling::td[1]");
    private By DeletedElement =By.xpath("//td[text()='Samsung galaxy s6']/following-sibling::td[2]/a");
    private By nameOfProduct=By.xpath("//tbody[@id='tbodyid']/tr/td[2]");

    //Methods
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

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));

        return this;
    }


    public String getThankYouMessage() {
        // Wait for the success Message to be Present
        Waits.waitForElementPresent(driver,popAlert);
        return driver.findElement(messageSuccess).getText();
    }

    public void clickOnOkButton()  {
        Enventhandler.waitForSuccessAnimation(driver,markIcon);
        Waits.clickOnElement(driver,submitButton);
    }

    /////////////////////////////////////////////////


    public int GetTotalPrice() {
        System.out.println("Attempting to get total price..."); // طباعة تتبع


        Waits.waitForElementPresent(driver, totalPrice);
        System.out.println("Price element found in DOM");


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
        System.out.println("Price element is now visible");


        String priceText = driver.findElement(totalPrice).getText().trim();
        System.out.println("Raw price text: '" + priceText + "'");

        if(priceText.isEmpty()) {
            throw new RuntimeException("Price text is empty!");
        }


        String cleanedText = priceText.replaceAll("[^0-9]", "");
        System.out.println("Cleaned price text: '" + cleanedText + "'");

        if(cleanedText.isEmpty()) {
            throw new RuntimeException("No numeric values found in price!");
        }

        try {
            int price = Integer.parseInt(cleanedText);
            System.out.println("Successfully parsed price: " + price);
            return price;
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse price: " + cleanedText);
            throw e;
        }
    }

    public int GetPriceOfDeletedElement()
    {
        Waits.waitForElementPresent(driver,priceofDeletedElement);
        String priceText= driver.findElement(priceofDeletedElement).getText();
        priceText = priceText.replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }



    public void DeleteItem()
    {
        Waits.clickOnElement(driver,DeletedElement);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DeletedElement));
    }
    public List<String> getProductNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOfProduct));

        List<WebElement> nameElements = driver.findElements(nameOfProduct);
        List<String> productNames = new ArrayList<>();

        for (WebElement element : nameElements) {
            productNames.add(element.getText().trim());
        }

        return productNames;
    }
    }





