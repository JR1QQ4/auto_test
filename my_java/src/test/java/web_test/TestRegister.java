package web_test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegister extends Base {
    @Test(dataProvider = "negativeData")
    public void test_register_negative(String phoneNumber, String password, String passwordConfirm, String verifyCode, String expectedErrorMsg) {
        // 访问注册页面
        driver.get("http://dongqunren.gitee.io/test/src/register.html");

        UILibraryUtil.getElementByKeyword("注册页面", "手机号").sendKeys(phoneNumber);
        UILibraryUtil.getElementByKeyword("注册页面", "密码").sendKeys(password);
        UILibraryUtil.getElementByKeyword("注册页面", "密码确认").sendKeys(passwordConfirm);
        UILibraryUtil.getElementByKeyword("注册页面", "验证码").sendKeys(verifyCode);

//        // 输入手机号
//        driver.findElement(By.id("js_phone")).sendKeys(phoneNumber);
//        // 输入密码
//        driver.findElement(By.id("js_password")).sendKeys(password);
//        // 确认密码
//        driver.findElement(By.id("js_password_confirm")).sendKeys(passwordConfirm);
//        // 验证码
//        driver.findElement(By.id("js_pin")).sendKeys(verifyCode);

        // 点击注册
        driver.findElement(By.id("js_register")).click();

//        String phone_info = UILibraryUtil.getElementByKeyword("注册页面", "手机号错误信息").getText();
//        String pwd_info = UILibraryUtil.getElementByKeyword("注册页面", "密码错误信息").getText();
//        String pwd_confirm_info = UILibraryUtil.getElementByKeyword("注册页面", "密码确认错误信息").getText();
//        String pin_info = UILibraryUtil.getElementByKeyword("注册页面", "验证码错误信息").getText();

        String phone_info = driver.findElement(By.id("phone_info")).getText();
        String pwd_info = driver.findElement(By.id("pwd_info")).getText();
        String pwd_confirm_info = driver.findElement(By.id("pwd_confirm_info")).getText();
        String pin_info = driver.findElement(By.id("pin_info")).getText();

        String actualErrorMsg = "{\"phone_info\":\"" + phone_info + "\", \"pwd_info\":\"" + pwd_info +
                "\", \"pwd_confirm_info\":\"" + pwd_confirm_info + "\", \"pin_info\":\"" + pin_info + "\"}";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @DataProvider(name = "negativeData")
    public Object[][] getNegativeData() {
        String[] columnNames = {"PhoneNumber", "Password", "PasswordConfirm", "VerifyCode", "ExpectedErrorMsg"};
        Object[][] objects = RegisterUtil.getNegativeOrPositiveData("0", columnNames);
        return objects;
    }

    @Test(dataProvider = "positiveData")
    public void test_register_positive(String phoneNumber, String password, String passwordConfirm, String verifyCode) {
        // 访问注册页面
        driver.get("http://dongqunren.gitee.io/test/src/register.html");

        UILibraryUtil.getElementByKeyword("注册页面", "手机号").sendKeys(phoneNumber);
        UILibraryUtil.getElementByKeyword("注册页面", "密码").sendKeys(password);
        UILibraryUtil.getElementByKeyword("注册页面", "密码确认").sendKeys(passwordConfirm);
        UILibraryUtil.getElementByKeyword("注册页面", "验证码").sendKeys(verifyCode);

//        // 输入手机号
//        driver.findElement(By.id("js_phone")).sendKeys(phoneNumber);
//        // 输入密码
//        driver.findElement(By.id("js_password")).sendKeys(password);
//        // 确认密码
//        driver.findElement(By.id("js_password_confirm")).sendKeys(passwordConfirm);
//        // 验证码
//        driver.findElement(By.id("js_pin")).sendKeys(verifyCode);

        // 点击注册
        driver.findElement(By.id("js_register")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String urlContains  = "login.html";
        Assertion.assertUrlContains(urlContains);
    }

    @DataProvider(name = "positiveData")
    public Object[][] getPositiveData() {
        String[] columnNames = {"PhoneNumber", "Password", "PasswordConfirm", "VerifyCode"};
        Object[][] objects = RegisterUtil.getNegativeOrPositiveData("1", columnNames);
        return objects;
    }
}
