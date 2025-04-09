package Tests;

import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_ProductItemPage;
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
import java.util.List;

public class AddingProductsFromTwoCategory {
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


    @Test
    public void validLogin() {
        new P01_LoginPage(driver).clickONLogin()
                .enterUsername(DataUtil.getJsonData("TestData","LoginCred","username"))
                .enterPassword(DataUtil.getJsonData("TestData","LoginCred","password"))
                .clickOnLoginButton();

        Assert.assertTrue(new P01_LoginPage(driver).isWelcomeMessageDisplayed(), "Assert True for Welcome Message not displayed success");
    }

    @Test(dependsOnMethods = "validLogin")
    public void clickOnProduct() {
        new P02_HomePage(driver).clickOnSpecificItem();
    }

    @Test(dependsOnMethods = "clickOnProduct")
    public void addingA_First_ItemToCart() {
        new P03_ProductItemPage(driver)
                .clickOnCartButton()
                .waitForAlertPresent();
    }

    @Test(dependsOnMethods = "addingA_First_ItemToCart")
    public void goToHome_clickAnotherProduct() throws FileNotFoundException {
        new P02_HomePage(driver).clickOnHomeButton();
    }

    @Test(dependsOnMethods ="goToHome_clickAnotherProduct")
    public void clickOnCategory(){
        new P02_HomePage(driver).ClickOnMonitors();
    }

    @Test(dependsOnMethods = "clickOnCategory")
    public void clickOnMonitorProduct() {
        new P02_HomePage(driver).clickOnMonitorProduct();
    }


    @Test(dependsOnMethods = "clickOnMonitorProduct")
    public void addingA_second_ItemToCart() {
        new P03_ProductItemPage(driver)
                .clickOnCartButton()
                .waitForAlertPresent();
    }
    @Test(dependsOnMethods = "addingA_second_ItemToCart")
    public void Gotocart() throws FileNotFoundException {

        String Product1 = "Samsung galaxy s6";
        String Product2 = "ASUS Full HD";

        P04_CartPage cartPage = new P04_CartPage(driver);
        cartPage.clickOnCartButton();
        List<String> actualProductNames = cartPage.getProductNames();

        System.out.println("products in cart are " + actualProductNames);


        boolean product1Found = false;
        for (String name : actualProductNames) {
            if (name.trim().equalsIgnoreCase(Product1.trim())) {
                product1Found = true;
                break;
            }
        }

        boolean product2Found = false;
        for (String name : actualProductNames) {
            if (name.trim().equalsIgnoreCase(Product2.trim())) {
                product2Found = true;
                break;
            }
        }
        Assert.assertTrue(product1Found, "product1" + Product1 + "");
        Assert.assertTrue(product2Found, "product2" + Product2 + "");


    }

}
