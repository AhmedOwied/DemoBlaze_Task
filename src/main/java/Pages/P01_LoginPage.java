package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P01_LoginPage {
    //Variables
    private WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //navigate
    public void navigateToLoginPage() {
        driver.navigate().to("https://www.demoblaze.com/");
    }


    //Locators
    private By login = By.linkText("Log in");
    private By userNameInput = By.id("loginusername");
    private By passwordInput = By.id("loginpassword");
    private By loginButton = By.cssSelector("button[onclick='logIn()']");
    private By welcomeMessage = By.id("nameofuser");


    //Methods
    public P01_LoginPage clickONLogin() {
        Waits.clickOnElement(driver, login);
        return this;
    }

    public P01_LoginPage enterUsername(String name) {
        Waits.sendData(driver, userNameInput, name);
        return this;
        //driver.findElement(userNameInput).sendKeys(name);
    }

    public P01_LoginPage enterPassword(String password) {
        Waits.sendData(driver, passwordInput, password);
        return new P01_LoginPage(driver);
        //driver.findElement(userNameInput).sendKeys(password);
    }

    public void clickOnLoginButton() {
        Waits.clickOnElement(driver, loginButton);

        //driver.findElement(userNameInput).click();
    }

    public boolean isWelcomeMessageDisplayed() {
        //TODO:: Don`t Forget enhancement in this Code
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
        return driver.findElement(welcomeMessage).isDisplayed();
    }
}
