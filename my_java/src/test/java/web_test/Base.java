package web_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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
            desiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");
            InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
            internetExplorerOptions.merge(desiredCapabilities);

            driver = new InternetExplorerDriver(internetExplorerOptions);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");

            driver = new FirefoxDriver();
        } else if ("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");

            driver = new ChromeDriver();
        } else {
            System.out.println("browser: " + browser + ", 暂不支持。");
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    @AfterSuite
    public void close() {
        driver.quit();
    }
}
