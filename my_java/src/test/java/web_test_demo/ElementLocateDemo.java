package web_test_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import web_test.Base;

import javax.xml.bind.Element;
import java.util.List;
import java.util.Set;

public class ElementLocateDemo extends Base {
    @Test
    public void test_locate() {
//        1. id、name、className、tagName 选择器
//        driver.navigate().to("http://dongqunren.gitee.io/test/src/register.html");
//        driver.findElement(By.id("js_phone")).sendKeys("13566668888");
//        driver.findElement(By.name("password")).sendKeys("123456");
//        driver.findElements(By.className("input_text")).get(2).sendKeys("123456");
//        driver.findElements(By.tagName("input")).get(3).sendKeys("6666");

//        2. 点击事件
//        driver.navigate().to("https://www.imooc.com/");
////        driver.findElement(By.linkText("实战课")).click();
//        driver.findElement(By.partialLinkText("免费")).click();

//        3.cssSelector
//        driver.navigate().to("http://dongqunren.gitee.io/test/src/register.html");
//        driver.findElement(By.cssSelector("#js_phone")).sendKeys("13566668888");
//        driver.findElement(By.cssSelector("#js_password")).sendKeys("123456");
//        driver.findElement(By.cssSelector("input[placeholder='密码确认']")).sendKeys("123456");
//        driver.findElements(By.cssSelector(".input_pin")).get(0).sendKeys("6666");

//        4.xpath
//        driver.navigate().to("http://dongqunren.gitee.io/test/src/register.html");
//        driver.findElement(By.xpath("//*[@id=\"js_phone\"]")).sendKeys("13566668888");
//        driver.findElement(By.xpath("//*[@id=\"js_password\"]")).sendKeys("123456");
//        driver.findElement(By.xpath("//*[@placeholder=\"密码确认\"]")).sendKeys("123456");
//        driver.findElement(By.xpath("//input[@name=\"pin\"]")).sendKeys("6666");

//        driver.navigate().to("https://www.imooc.com/");
//        driver.findElement(By.xpath("//*[text()=\"企业服务\"]")).click();

//        5.alert
//        driver.get("https://www.w3school.com.cn/tiy/t.asp?f=hdom_alert");
//        driver.switchTo().frame("iframeResult");
//        driver.findElements(By.tagName("input")).get(0).click();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.switchTo().alert().accept();

//        6.frame
//        driver.get("http://dongqunren.gitee.io/test/src/demo/a.html");
//        driver.findElement(By.xpath("//*[@id=\"a_text\"]")).sendKeys("aaa");
//        WebElement bFrameElement = driver.findElement(By.xpath("/html/body/div/div/iframe"));
//        driver.switchTo().frame(bFrameElement);
//        driver.findElement(By.xpath("//*[@id=\"b_text\"]")).sendKeys("bbb");
//        driver.switchTo().frame(0);
//        driver.findElement(By.xpath("//*[@id=\"c_text\"]")).sendKeys("ccc");

//        7.isDisplayed isEnabled isSelected
//        driver.get("http://dongqunren.gitee.io/test/src/demo/a.html");
//        System.out.println("是否可见: " + driver.findElement(By.xpath("//*[@id=\"optionsRadios3\"]")).isDisplayed());
//        System.out.println("是否可编辑: " + driver.findElement(By.xpath("//*[@id=\"optionsRadios3\"]")).isEnabled());
//        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"select\"]")));
//        List<WebElement> options = select.getOptions();
//        for (WebElement webElement:
//             options) {
//            System.out.println(webElement.getTagName() + " : " + webElement.getText() + ", isSelected: " + webElement.isSelected());
//        }

//        8.getWindowHandle getWindowHandles 切换窗口
//        driver.get("http://dongqunren.gitee.io/test/");
//        String handleIndex = driver.getWindowHandle();
//        System.out.println("handleIndex: " + handleIndex);
//        driver.findElement(By.linkText("登录")).click();
//        String handleShiLi = driver.getWindowHandle();
//        System.out.println("handleShiLi: " + handleShiLi);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.findElement(By.linkText("注册")).click();
//        Set<String> allHandles = driver.getWindowHandles();
//        for (String handle:
//             allHandles) {
////            if (handle.equals(handleIndex)){
////                continue;
////            }
//            driver.switchTo().window(handle);
//            String windowTitle = driver.getTitle();
//            System.out.println("windowTitle: " + windowTitle);
//        }

//        9.Actions
//        driver.get("http://www.treejs.cn/v3/demo.php#_301");
//        driver.switchTo().frame("demoIframe");
//        WebElement source = driver.findElement(By.id("treeDemo_4_a"));
//        WebElement target = driver.findElement(By.id("treeDemo_7"));
//        Actions actions = new Actions(driver);
//        actions.dragAndDrop(source, target).build().perform();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        10.date
//        driver.navigate().to("http://dongqunren.gitee.io/test/src/demo/a.html");
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//        javascriptExecutor.executeScript("document.getElementById(\"date\").value = \"2020-12-03\"");
    }
}
