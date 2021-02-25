package web_test_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBrowser {
    public void sendKeysToBaidu(WebDriver driver){
        driver.get("http://www.baidu.com");
        driver.findElement(By.id("kw")).sendKeys("selenium-java web自动化测试");
    }

    @Parameters(value = {"browser"})
    @Test
    public void test(String browser) {
        System.out.println(browser + " start...");

        WebDriver driver = null;
        if ("ie".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.ie.driver", "C:\\webdriver\\IEDriverServer.exe");

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            desiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");
            InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
            internetExplorerOptions.merge(desiredCapabilities);

            driver = new InternetExplorerDriver(internetExplorerOptions);
            sendKeysToBaidu(driver);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");

            driver = new FirefoxDriver();
            sendKeysToBaidu(driver);
        } else if ("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");

            driver = new ChromeDriver();
            sendKeysToBaidu(driver);
        } else {
            System.out.println("browser: " + browser + ", 暂不支持。");
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}