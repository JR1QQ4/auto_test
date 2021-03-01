package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import web_test.Base;

import java.io.File;
import java.io.IOException;

/**
 * 用于保存截图
 */
public class ScreenshotUtils {
    public static File saveScreenshot(String filePath) {
        File screenshot = null;
        if (Base.driver instanceof FirefoxDriver){
            FirefoxDriver firefoxDriver = (FirefoxDriver) Base.driver;
            screenshot = firefoxDriver.getScreenshotAs(OutputType.FILE);
        }else if(Base.driver instanceof InternetExplorerDriver){
            InternetExplorerDriver internetExplorerDriver = (InternetExplorerDriver) Base.driver;
            screenshot = internetExplorerDriver.getScreenshotAs(OutputType.FILE);
        }else if(Base.driver instanceof ChromeDriver){
            ChromeDriver chromeDriver = (ChromeDriver) Base.driver;
            screenshot = chromeDriver.getScreenshotAs(OutputType.FILE);
        }

        // screenshot: C:/Users/username/AppData/Local/Tmp/screenshot1374647327982150283.png
        System.out.println("screenshot: " + screenshot);

        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(screenshot, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }
}
