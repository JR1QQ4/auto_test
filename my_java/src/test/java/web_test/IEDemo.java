package web_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.ie.driver", "C:\\webdriver\\IEDriverServer.exe");
        // 忽略保护模式
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.merge(desiredCapabilities);
        // 1. 启动火狐浏览器
        WebDriver driver = new InternetExplorerDriver(internetExplorerOptions);
        // 2.访问指定的网址
        driver.get("http://www.baidu.com");
        // 3.对网页进行操作
        driver.findElement(By.id("kw")).sendKeys("selenium-java web自动化测试");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4.退出，不退出会占用内存
        driver.quit();
    }
}
