package web_test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLogin extends Base {
    @Test(dataProvider = "negativeData")
    public void test_login_negative(String phoneNumber, String password, String expectedErrorMsg){
        // 访问注册页面
        driver.get("http://dongqunren.gitee.io/test/src/login.html");

        UILibraryUtil.getElementByKeyword("登录页面", "手机号").sendKeys(phoneNumber);
        UILibraryUtil.getElementByKeyword("登录页面", "密码").sendKeys(password);
        UILibraryUtil.getElementByKeyword("登录页面", "登录").click();

        String phone_info = driver.findElement(By.id("phone_info")).getText();
        String pwd_info = driver.findElement(By.id("pwd_info")).getText();

        String actualErrorMsg = "{\"phone_info\":\"" + phone_info + "\", \"pwd_info\":\"" + pwd_info + "\"}";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @DataProvider
    public Object[][] negativeData(){
        String[] columnNames = {"PhoneNumber", "Password", "ExpectedErrorMsg"};
        Object[][] objectArrays = LoginUtil.getNegativeOrPositiveData("0", columnNames);
        return objectArrays;
    }

    @Test(dataProvider = "positiveData")
    public void test_login_positive(String phoneNumber, String password){
//        // 访问注册页面
//        driver.get("http://dongqunren.gitee.io/test/src/login.html");
//
//        UILibraryUtil.getElementByKeyword("登录页面", "手机号").sendKeys(phoneNumber);
//        UILibraryUtil.getElementByKeyword("登录页面", "密码").sendKeys(password);
//        UILibraryUtil.getElementByKeyword("登录页面", "登录").click();

        to(PropertiesUtil.getLoginUrl());
        sendKeys("登录页面", "手机号", phoneNumber);
        sendKeys("登录页面", "密码", password);
        click("登录页面", "登录");

        String urlContains  = "index.html";
        Assertion.assertUrlContains(urlContains);
    }
    @DataProvider
    public Object[][] positiveData(){
        String[] columnNames = {"PhoneNumber", "Password"};
        Object[][] objectArrays = LoginUtil.getNegativeOrPositiveData("1", columnNames);
        return objectArrays;
    }
}
