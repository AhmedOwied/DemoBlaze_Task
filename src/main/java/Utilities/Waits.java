package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    //TODO:: wait for to be element present

    public static WebElement waitForElementPresent(WebDriver driver, By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    //ToDo:: Convert to WebElement
    public static WebElement byToWebElement(WebDriver driver , By locator){
        return driver.findElement(locator);
    }

    //TODO:: clicking ON Element
    public  static void clickOnElement(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //TODO:: Sending Data to Element
    public  static void sendData(WebDriver driver, By locator,String text){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    //TODO:: Wait for alert is present
    public static void acceptAlert(WebDriver driver ){
        new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
    }
}
