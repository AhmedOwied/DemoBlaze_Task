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

public class NavigationbackAfterLogout {
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

    @Test(dependsOnMethods ="validLogin")
    public void clickOnCategory(){
        new P02_HomePage(driver).ClickOnMonitors();
    }

    @Test(dependsOnMethods = "clickOnCategory")
    public void clickOnProduct() {
        new P02_HomePage(driver).clickOnMonitorProduct();
    }

    @Test(dependsOnMethods = "clickOnProduct")
    public void addingA_First_ItemToCart() {
        new P03_ProductItemPage(driver)
                .clickOnCartButton()
                .waitForAlertPresent();
    }
    @Test(dependsOnMethods = "addingA_First_ItemToCart")
    public void Gotocart() throws FileNotFoundException {
        P04_CartPage cartPage = new P04_CartPage(driver);
        cartPage.clickOnCartButton();

        String expectedName = "ASUS Full HD";
        List<String> actualProductNames = cartPage.getProductNames();

        System.out.println("المنتجات الموجودة في العربة: " + actualProductNames);


        boolean found = false;
        for (String name : actualProductNames) {
            if (name.trim().equalsIgnoreCase(expectedName.trim())) {
                found = true;
                break;
            }
        }

        Assert.assertEquals(found, true);


    }

@Test(dependsOnMethods = "Gotocart")
public void logOut()
{
    new P02_HomePage(driver).clickOnLogoutBtn().alreadyLogout();
    Assert.assertEquals(new P02_HomePage(driver).alreadyLogout(),"Log in");

}

@Test(dependsOnMethods = "logOut")
    public void navigatebackLogin()
{
    driver.navigate().back();
    Assert.assertEquals(new P02_HomePage(driver).alreadyLogout(),"Log in","system enter in account after logout ");




}
}

