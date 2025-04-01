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

    private By itemOfProduct= By.linkText("Samsung galaxy s6");

    //methods

    public void clickOnSpecificItem(){
        Waits.clickOnElement(driver,itemOfProduct);
    }
}
