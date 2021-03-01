package web_test;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Base {
    public static WebDriver driver;

    @Parameters(value = {"browser"})
    @Test
    public void test(String browser) {
        System.out.println(browser + " start...");

        if ("ie".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.ie.driver", "C:\\webdriver\\IEDriverServer.exe");

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            desiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");
            InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
            internetExplorerOptions.merge(desiredCapabilities);

            driver = new InternetExplorerDriver(internetExplorerOptions);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(desiredCapabilities);

            driver = new FirefoxDriver(firefoxOptions);
        } else if ("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(desiredCapabilities);

            driver = new ChromeDriver(chromeOptions);
        } else {
            System.out.println("browser: " + browser + ", 暂不支持。");
        }
    }

    @AfterSuite
    public void close() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    /**
     * 跳转页面
     * @param url 页面地址
     */
    public void to(String url){
        driver.get(url);
    }

    /**
     * 给输入框发送值
     * @param keyword 页面名称
     * @param uiElementKeyword 元素名称
     * @param content 要发送过的值
     */
    public void sendKeys(String keyword, String uiElementKeyword, String content){
        UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword).sendKeys(content);
    }

    /**
     * 点击元素
     * @param keyword 页面关键字
     * @param uiElementKeyword 元素定位关键字
     */
    public void click(String keyword, String uiElementKeyword){
        UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword).click();
    }

    /**
     * 获取元素的文本值
     * @param keyword 页面关键字
     * @param uiElementKeyword 元素定位关键字
     * @return 元素的文本值
     */
    public String getText(String keyword, String uiElementKeyword){
        WebElement webElement = UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword);
        if (webElement != null) {
            return webElement.getText();
        }
        return "";
    }

    /**
     * 获取元素
     * @param keyword 页面元素关键字
     * @param uiElementKeyword 元素定位关键字
     * @return 定位到的元素
     */
    public WebElement getElement(String keyword, String uiElementKeyword){
        return UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword);
    }

    /**
     * 却换到指定 iframe 中
     * @param keyword 页面关键字
     * @param uiElementKeyword 元素定位关键字
     */
    public void switchToIframe(String keyword, String uiElementKeyword){
        WebElement webElement = UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword);
        driver.switchTo().frame(webElement);
    }

    /**
     * 通过 select 选项的值选择 select 元素
     * @param keyword 页面关键字
     * @param uiElementKeyword 元素定位关键字
     * @param optionText select元素的选项的值
     */
    public void selectByOptionText(String keyword, String uiElementKeyword, String optionText){
        WebElement webElement = UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword);
        Select select = new Select(webElement);
        select.selectByVisibleText(optionText);
    }

    /**
     * 根据 option 的 value 值选中下拉框的某一个选项
     * @param keyword 页面关键字
     * @param uiElementKeyword 元素定位关键字
     * @param optionValue 选项的值
     */
    public void selectByOptionValue(String keyword, String uiElementKeyword, String optionValue){
        WebElement webElement = UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword);
        Select select = new Select(webElement);
        select.selectByValue(optionValue);
    }

    /**
     * 当页面加载完成后进行点击
     * @param keyword 页面关键字
     * @param uiElementKeyword 元素定位关键字
     */
    public void clickWhilePageIsLoaded(String keyword, String uiElementKeyword){
        boolean isPageLoaded = true;
        int range = 10;
        WebDriverWait webDriverWait = new WebDriverWait(driver, range);
        try{
            isPageLoaded = (boolean) webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'"));
        }catch (Exception e){
            isPageLoaded = false;
            System.out.println("页面加载超过"+range+"秒，后续页面上的元素操作不保证能达到预期");
        }
        System.out.println("页面是否已经加载完毕: " + isPageLoaded);
        WebElement webElement = UILibraryUtil.getElementByKeyword(keyword, uiElementKeyword);
        webElement.click();
    }
}
