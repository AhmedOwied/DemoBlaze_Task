package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Enventhandler {
        public static void waitForAnimationComplete(WebDriver driver, By locator) {
            WebElement element = Waits.waitForElementPresent(driver, locator);

            // JavaScript that properly handles the WebElement to DOM element conversion
            String script =
                    "var element = arguments[0];" +
                            "var callback = arguments[1];" +
                            "" +
                            "if (window.getComputedStyle(element).animationName === 'none') {" +
                            "   callback(true);" +
                            "   return;" +
                            "}" +
                            "" +
                            "var handler = function() {" +
                            "   element.removeEventListener('animationend', handler);" +
                            "   callback(true);" +
                            "};" +
                            "" +
                            "element.addEventListener('animationend', handler);";

            // Use executeAsyncScript for proper Promise handling
            ((JavascriptExecutor)driver).executeAsyncScript(script, element);
        }

        // Specific method for success animation
        public static void waitForSuccessAnimation(WebDriver driver, By locator) {
            waitForAnimationComplete(driver, locator);
        }

    }

