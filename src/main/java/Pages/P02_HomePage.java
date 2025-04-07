package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_HomePage {
    //variables
    private WebDriver driver;

    public P02_HomePage(WebDriver driver) {
        this.driver=driver;
    }
    //locators
    private By loginBtn = By.id("login2");

    private By contactIcon = By.linkText("Contact");
    private By emailfield = By.id("recipient-email");
    private By namefield = By.id("recipient-name");
    private By messagefield = By.id("message-text");
    private By sendmessageBtn = By.xpath("//button[@onclick=\"send()\"]");
    private By logoutBtn =  By.id("logout2");


    private By itemOfProduct= By.linkText("Samsung galaxy s6");

    //methods

    public void clickOnSpecificItem(){
        Waits.clickOnElement(driver,itemOfProduct);
    }

    public P02_HomePage clickonContactBtn(){
        Waits.clickOnElement(driver,contactIcon);
        return this;
    }

    public P02_HomePage enterEmail(String email){
        Waits.sendData(driver,emailfield,email);
        return this;

    }

    public P02_HomePage enterName(String name){
        Waits.sendData(driver,namefield,name);
        return this;

    }

    public P02_HomePage enterMessage(String message){
        Waits.sendData(driver,messagefield,message);
        return this;

    }

    public P02_HomePage clickOnSendMessageBtn(){
        Waits.clickOnElement(driver,sendmessageBtn);
        return this;

    }

    public P02_HomePage waitForAlertPresent(){
        Waits.acceptAlert(driver);
        return this;

    }

    public P02_HomePage clickOnLogoutBtn(){
        Waits.clickOnElement(driver,logoutBtn);
        return this;
    }

    public String alreadyLogout() {
        Waits.waitForElementPresent(driver,loginBtn);
        return driver.findElement(loginBtn).getText();
    }

}




