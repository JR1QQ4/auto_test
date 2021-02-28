package web_test;

import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegister extends Base {
    @Test(dataProvider = "negativeData")
    public void test_register_negative(String phoneNumber, String password, String passwordConfirm, String verifyCode, String expectedErrorMsg) {
        // 访问注册页面
        driver.get("http://dongqunren.gitee.io/test/src/register.html");
        // 输入手机号
        driver.findElement(By.id("js_phone")).sendKeys(phoneNumber);
        // 输入密码
        driver.findElement(By.id("js_password")).sendKeys(password);
        // 确认密码
        driver.findElement(By.id("js_password_confirm")).sendKeys(passwordConfirm);
        // 验证码
        driver.findElement(By.id("js_pin")).sendKeys(verifyCode);
        // 点击注册
        driver.findElement(By.id("js_register")).click();

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
        // 输入手机号
        driver.findElement(By.id("js_phone")).sendKeys(phoneNumber);
        // 输入密码
        driver.findElement(By.id("js_password")).sendKeys(password);
        // 确认密码
        driver.findElement(By.id("js_password_confirm")).sendKeys(passwordConfirm);
        // 验证码
        driver.findElement(By.id("js_pin")).sendKeys(verifyCode);
        // 点击注册
        driver.findElement(By.id("js_register")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login.html"));
    }

    @DataProvider(name = "positiveData")
    public Object[][] getPositiveData() {
        String[] columnNames = {"PhoneNumber", "Password", "PasswordConfirm", "VerifyCode"};
        Object[][] objects = RegisterUtil.getNegativeOrPositiveData("1", columnNames);
        return objects;
    }
}
