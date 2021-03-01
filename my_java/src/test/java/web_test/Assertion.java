package web_test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * 用于断言的类
 */
public class Assertion {
    /**
     * 判断当前页面 url 是否包含指定的内容
     *
     * @param urlContains 要匹配的内容
     */
    public static void assertUrlContains(String urlContains) {
        WebDriverWait webDriverWait = new WebDriverWait(Base.driver, 8);
        boolean isDirectedToLogin = true;
        try {
            webDriverWait.until(ExpectedConditions.urlContains("login.html"));
        } catch (Exception e) {
            isDirectedToLogin = false;
        }
        Assert.assertTrue(isDirectedToLogin);
    }

    /**
     * 文本值出现在页面的指定元素中
     * @param webElement 指定的元素
     * @param text 文本值
     */
    public static void assertTextPresent(WebElement webElement, String text) {
        WebDriverWait webDriverWait = new WebDriverWait(Base.driver, 8);
        boolean textToBePresentInElement = true;
        try {
            webDriverWait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
        }catch (Exception e) {
            textToBePresentInElement = false;
        }
        Assert.assertTrue(textToBePresentInElement);
    }

    public static void assertElementToBeClickable(WebElement webElement){
        WebDriverWait webDriverWait = new WebDriverWait(Base.driver, 8);
        boolean elementToBeClickable = true;
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        }catch (Exception e){
            elementToBeClickable = false;
        }
        Assert.assertTrue(elementToBeClickable);
    }

}
