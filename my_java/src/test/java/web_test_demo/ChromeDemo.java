package web_test_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        // 1. 启动火狐浏览器
        WebDriver driver = new ChromeDriver();
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
