package Tests;

import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_ProductItemPage;
import Pages.P04_CartPage;
import Utilities.DataUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

public class e2e_Scenario {
    private WebDriver driver;
    private P01_LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Read -> Document
        driver = new EdgeDriver(options);
        loginPage=new P01_LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    //tests
    @Test
    public void validLogin() throws FileNotFoundException {
        new P01_LoginPage(driver).clickONLogin()
               .enterUsername(DataUtil.getJsonData("LoginCredential","username"))
                .enterPassword(DataUtil.getJsonData("LoginCredential","password"))
                .clickOnLoginButton();

        Assert.assertTrue(new P01_LoginPage(driver).isWelcomeMessageDisplayed(), "Element not displayed success");
        //System.out.println("Welcome message status: " + new P01_LoginPage(driver).isWelcomeMessageDisplayed());
    }

    @Test(dependsOnMethods ="validLogin")
    public void clickOnProduct(){
        new P02_HomePage(driver).clickOnSpecificItem();
    }

    @Test(dependsOnMethods ="clickOnProduct")
    public void addingItemToCart(){
         new P03_ProductItemPage(driver)
                 .clickOnCartButton()
                 .waitForAlertPresent();
    }

    @Test(dependsOnMethods ="addingItemToCart")
    public void checkOutOrder() throws FileNotFoundException {
        new P04_CartPage(driver).clickOnCartButton()
                .clickOnPlaceOrder()
                .enterName(DataUtil.getJsonData("CheckOutForm","Name"))
                .enterCountry(DataUtil.getJsonData("CheckOutForm","Country"))
                .enterCity(DataUtil.getJsonData("CheckOutForm","City"))
                .enterCreditCard(DataUtil.getJsonData("CheckOutForm","CreditCard"))
                .enterMonth(DataUtil.getJsonData("CheckOutForm","Month"))
                .enterYear(DataUtil.getJsonData("CheckOutForm","Year"))
                .clickOnPurchaseButton()
                .clickOnSubmitButton();  //TODO:: Wrong When Click on Ok button !!

        Assert.assertEquals(new P04_CartPage(driver).getThankYouMessage(),"Thank you for your purchase!");



    }


    @AfterClass
    public void tearDown(){
        //driver.quit();
    }
}
