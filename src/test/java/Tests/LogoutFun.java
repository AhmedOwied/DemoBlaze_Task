package Tests;

import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Utilities.DataUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class LogoutFun {
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

    @Test
    public void validLogin() throws FileNotFoundException {
        new P01_LoginPage(driver).clickONLogin()
                .enterUsername(DataUtil.getJsonData("TestData","LoginCred","username"))
                .enterPassword(DataUtil.getJsonData("TestData","LoginCred","password"))
                .clickOnLoginButton();

        Assert.assertTrue(new P01_LoginPage(driver).isWelcomeMessageDisplayed(), "Element not displayed success");
        //System.out.println("Welcome message status: " + new P01_LoginPage(driver).isWelcomeMessageDisplayed());
    }

@Test(dependsOnMethods ="validLogin")
    public void contactFunctionality() throws FileNotFoundException{
        new P02_HomePage(driver).clickOnContactBtn()
                .enterEmail(DataUtil.getJsonData("TestData","ContactData","email"))
                .enterName(DataUtil.getJsonData("TestData","ContactData","name")).
                enterMessage(DataUtil.getJsonData("TestData","ContactData","message"))
                .clickOnSendMessageBtn().waitForAlertPresent()
                .clickOnLogoutBtn();

    Assert.assertEquals(new P02_HomePage(driver).alreadyLogout(),"Log in");

//   Assert.assertEquals(driver.findElement(By.id("login2")).getText(),"Log in");





}

}
