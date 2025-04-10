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

        driver.manage().window().maximize();
        loginPage=new P01_LoginPage(driver);
        loginPage.navigateToLoginPage();

        }

    //tests

    @Test(invocationCount = 3)
    public void e2e_scenario_One(){
        new P01_LoginPage(driver).clickONLogin()
                .enterUsername(DataUtil.getJsonData("TestData","LoginCred","username"))
                .enterPassword(DataUtil.getJsonData("TestData","LoginCred","password"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(driver).isWelcomeMessageDisplayed(), "Assert True for Welcome Message not displayed success");
        //
        new P02_HomePage(driver).clickOnSpecificItem();
        //
        new P03_ProductItemPage(driver)
                .clickOnCartButton()
                .waitForAlertPresent();
        ////
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
        Assert.assertEquals(cart.getThankYouMessage(),"Thank you for your purchase!","Assert Equals that the success message for CheckOut is not displayed correctly");
        cart.clickOnOkButton();

        new P02_HomePage(driver).clickOnLogoutBtn();

    }


    @Test
    public void validLogin() {
        new P01_LoginPage(driver).clickONLogin()
                .enterUsername(DataUtil.getJsonData("TestData","LoginCred","username"))
                .enterPassword(DataUtil.getJsonData("TestData","LoginCred","password"))
                .clickOnLoginButton();

        Assert.assertTrue(new P01_LoginPage(driver).isWelcomeMessageDisplayed(), "Assert True for Welcome Message not displayed success");
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
    public void checkOutOrder() throws FileNotFoundException, InterruptedException {
        P04_CartPage cart=new P04_CartPage(driver);
        new P04_CartPage(driver).clickOnCartButton()
                .clickOnPlaceOrder()
                .enterName(DataUtil.getJsonData("TestData","CheckoutData","Name"))
                .enterCountry(DataUtil.getJsonData("TestData","CheckoutData","Country"))
                .enterCity(DataUtil.getJsonData("TestData","CheckoutData","City"))
                .enterCreditCard(DataUtil.getJsonData("TestData","CheckoutData","CreditCard"))
                .enterMonth(DataUtil.getJsonData("TestData","CheckoutData","Month"))
                .enterYear(DataUtil.getJsonData("TestData","CheckoutData","Year"))
                .clickOnPurchaseButton();

                Assert.assertEquals(cart.getThankYouMessage(),"Thank you for your purchase!","Assert Equals that the success message for CheckOut is not displayed correctly");
                cart.clickOnOkButton();

    }


    @AfterClass
    public void tearDown(){
      //  driver.quit();
    }


}
