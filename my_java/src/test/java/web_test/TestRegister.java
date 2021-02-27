package web_test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegister extends Base {
    @Test(dataProvider = "data")
    public void test_register(String phoneNumber, String password, String passwordConfirm, String verifyCode, String expectedErrorMsg){
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
        Assert.assertEquals(phone_info, expectedErrorMsg);
        Assert.assertEquals(pwd_info, expectedErrorMsg);
        Assert.assertEquals(pwd_confirm_info, expectedErrorMsg);
        Assert.assertEquals(pin_info, expectedErrorMsg);
    }

    @DataProvider(name = "data")
    public Object[][] dataArrays(){
        String[] columnNames = {"PhoneNumber", "Password", "PasswordConfirm", "VerifyCode", "ExpectedErrorMsg"};
        Object[][] objects = RegisterUtil.getNegativeOrPositiveData("0", columnNames);
        return objects;
    }
}
