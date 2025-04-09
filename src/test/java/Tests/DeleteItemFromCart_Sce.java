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
import org.testng.annotations.*;

import java.io.FileNotFoundException;

public class DeleteItemFromCart_Sce {
    private WebDriver driver;
    private P01_LoginPage loginPage;
    static int price;
    static int DeletedPrice;
    static int totalPrice;
   // P04_CartPage cartPage = new P04_CartPage(driver);

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
    @Parameters(value ={"Username","Password"})
    @Test
    public void validLogin(@Optional("test") String username, @Optional("test") String password) {
        new P01_LoginPage(driver).clickONLogin()
                .enterUsername(username)
                .enterPassword(password)
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
        new P02_HomePage(driver).clickOnHomeButton().clickOnAnotherItem();
    }

    @Test(dependsOnMethods = "goToHome_clickAnotherProduct")
    public void addingA_second_ItemToCart() {
        new P03_ProductItemPage(driver)
                .clickOnCartButton()
                .waitForAlertPresent();
    }

    @Test(dependsOnMethods = "addingA_second_ItemToCart")
    public void Gotocart() throws FileNotFoundException {
            /*
            P04_CartPage cartPage=new P04_CartPage(driver) ;

            cartPage.clickOnCartButton();
            System.out.println("Before getting price");
            price = cartPage.GetTotalPrice();
            System.out.println("After getting price: " + price);
*/


        System.out.println("Before getting price");
        price = new P04_CartPage(driver).clickOnCartButton().GetTotalPrice();
        System.out.println("After getting price: " + price);


    }


    @Test(dependsOnMethods = "Gotocart")
    public void DeleteItem() throws FileNotFoundException, InterruptedException {
           /*
            P04_CartPage cartPage=new P04_CartPage(driver) ;

            DeletedPrice=      cartPage.GetPriceOfDeletedElement();
            cartPage.DeleteItem();


*/

        DeletedPrice = new P04_CartPage(driver).GetPriceOfDeletedElement();

        new P04_CartPage(driver).DeleteItem();
        //    totalPrice=new P04_CartPage(driver).GetTotalPrice();
        Assert.assertEquals(price - DeletedPrice, 790);


    }


    @Test(dependsOnMethods = "DeleteItem")
    public void checkOutOrder() throws FileNotFoundException {
        P04_CartPage cart =new P04_CartPage(driver);
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

                cart.clickOnOkButton(); //TODO:: Wrong When Click on Ok button !!
    }


    @AfterClass
    public void tearDown() {
    }
}