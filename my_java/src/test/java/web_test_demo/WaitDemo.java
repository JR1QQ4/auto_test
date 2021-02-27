package web_test_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import web_test.Base;

import java.util.concurrent.TimeUnit;

public class WaitDemo extends Base {
    @Test
    public void test_2() throws InterruptedException {
//        driver.get("https://www.sina.com.cn/");
//        Thread.sleep(3000);
//        WebElement webElement = driver.findElement(By.xpath("//*[contains(text(), \"Copyright\")]"));
//        System.out.println(webElement.getTagName() + ": " + webElement.getText());

//        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);  // 设置页面加载的超时时间
//        driver.get("https://www.sina.com.cn/");

//        隐式等待
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://www.sina.com.cn/");

//        显式等待
//        driver.get("https://www.sina.com.cn/");
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
//        WebElement movieElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("电影")));
//        movieElement.click();
    }

    /**
     * 获取一个加载到 DOM 上的元素
     * @param by 定位
     * @return 元素
     */
    public WebElement getPresenceElement(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        try{
            WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            return webElement;
        }catch (Exception e) {
            System.out.println("定位元素超时异常");
            return null;
        }
    }

    /**
     * 获取一个可见的元素
     * @param by 定位
     * @return 元素
     */
    public WebElement getVisibilityElement(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        try{
            WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return webElement;
        }catch (Exception e) {
            System.out.println("定位元素超时异常");
            return null;
        }
    }

    public WebElement getElementWhilePageIsReady(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        try {
            String jsScript = "return document.readyState == \"complete\"";
            boolean isReady = (Boolean) webDriverWait.until(ExpectedConditions.jsReturnsValue(jsScript));
            if (isReady){
                return getVisibilityElement(by);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
