package web_test_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import web_test.Base;

import javax.xml.bind.Element;

public class ElementLocate extends Base {
    @Test
    public void test_locate() {
//        driver.navigate().to("http://dongqunren.gitee.io/test/src/register.html");
//        driver.findElement(By.id("js_phone")).sendKeys("13566668888");
//        driver.findElement(By.name("password")).sendKeys("123456");
//        driver.findElements(By.className("input_text")).get(2).sendKeys("123456");
//        driver.findElements(By.tagName("input")).get(3).sendKeys("6666");

//        driver.navigate().to("https://www.imooc.com/");
////        driver.findElement(By.linkText("实战课")).click();
//        driver.findElement(By.partialLinkText("免费")).click();

//        driver.navigate().to("http://dongqunren.gitee.io/test/src/register.html");
//        driver.findElement(By.cssSelector("#js_phone")).sendKeys("13566668888");
//        driver.findElement(By.cssSelector("#js_password")).sendKeys("123456");
//        driver.findElement(By.cssSelector("input[placeholder='密码确认']")).sendKeys("123456");
//        driver.findElements(By.cssSelector(".input_pin")).get(0).sendKeys("6666");

//        driver.navigate().to("http://dongqunren.gitee.io/test/src/register.html");
//        driver.findElement(By.xpath("//*[@id=\"js_phone\"]")).sendKeys("13566668888");
//        driver.findElement(By.xpath("//*[@id=\"js_password\"]")).sendKeys("123456");
//        driver.findElement(By.xpath("//*[@placeholder=\"密码确认\"]")).sendKeys("123456");
//        driver.findElement(By.xpath("//input[@name=\"pin\"]")).sendKeys("6666");

//        driver.navigate().to("https://www.imooc.com/");
//        driver.findElement(By.xpath("//*[text()=\"企业服务\"]")).click();

//        driver.get("https://www.w3school.com.cn/tiy/t.asp?f=hdom_alert");
//        driver.switchTo().frame("iframeResult");
//        driver.findElements(By.tagName("input")).get(0).click();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.switchTo().alert().accept();

//        driver.get("http://dongqunren.gitee.io/test/src/demo/a.html");
//        driver.findElement(By.xpath("//*[@id=\"a_text\"]")).sendKeys("aaa");
//        WebElement bFrameElement = driver.findElement(By.xpath("/html/body/div/div/iframe"));
//        driver.switchTo().frame(bFrameElement);
//        driver.findElement(By.xpath("//*[@id=\"b_text\"]")).sendKeys("bbb");
//        driver.switchTo().frame(0);
//        driver.findElement(By.xpath("//*[@id=\"c_text\"]")).sendKeys("ccc");

        driver.get("http://dongqunren.gitee.io/test/");
        driver.findElement(By.linkText("示例")).click();
        
    }
}
