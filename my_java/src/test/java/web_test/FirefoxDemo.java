package web_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");
        // 1. 启动火狐浏览器
        WebDriver driver = new FirefoxDriver();
        // 2.访问指定的网址
        driver.get("http://www.baidu.com");
        // 3.对网页进行操作
        driver.findElement(By.id("kw")).sendKeys("selenium-java web自动化测试");

        try {
            Thread.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4.退出，不退出会占用内存
        driver.quit();
    }
}
