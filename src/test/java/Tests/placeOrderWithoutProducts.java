package Tests;

import Pages.P01_LoginPage;
import Pages.P04_CartPage;
import Utilities.DataUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class placeOrderWithoutProducts {
    private WebDriver driver;
    private P01_LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Read -> Document
        driver = new ChromeDriver(options);
        loginPage = new P01_LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    //tests

    @Test
    public void validLogin() {
        new P01_LoginPage(driver).clickONLogin()
                .enterUsername(DataUtil.getJsonData("TestData","LoginCred","username"))
                .enterPassword(DataUtil.getJsonData("TestData","LoginCred","password"))
                .clickOnLoginButton();

        Assert.assertTrue(new P01_LoginPage(driver).isWelcomeMessageDisplayed(), "Assert True for Welcome Message not displayed success");
    }

    @Test(dependsOnMethods = "validLogin")
    public void Gotocart() throws FileNotFoundException {
       new P04_CartPage(driver).clickOnCartButton();

    }
    @Test(dependsOnMethods = "Gotocart")
    public void checkOutOrder() throws FileNotFoundException {
        P04_CartPage cart= new P04_CartPage(driver);
        new P04_CartPage(driver).clickOnCartButton()
                .clickOnPlaceOrder()
                .enterName(DataUtil.getJsonData("TestData","CheckoutData","Name"))
                .enterCountry(DataUtil.getJsonData("TestData","CheckoutData","Country"))
                .enterCity(DataUtil.getJsonData("TestData","CheckoutData","City"))
                .enterCreditCard(DataUtil.getJsonData("TestData","CheckoutData","CreditCard"))
                .enterMonth(DataUtil.getJsonData("TestData","CheckoutData","Month"))
                .enterYear(DataUtil.getJsonData("TestData","CheckoutData","Year"))
                .clickOnPurchaseButton();

        Assert.assertEquals(cart.getThankYouMessage(),"The cart is empty");
        cart.clickOnOkButton();




    }



}
