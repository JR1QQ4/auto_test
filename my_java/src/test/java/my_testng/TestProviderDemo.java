package my_testng;

import com.sun.tracing.ProviderName;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestProviderDemo {
    @Test(dataProvider = "dd")
    public void testMyProvider(String name, int age, String gender) {
        System.out.println("name=" + name + ", age=" + age + ", gender=" + gender);
    }

    @DataProvider
    public Object[][] dataProvider1() {
        return new Object[][]{
                {"张三", 28, "男"},
                {"韩梅梅", 25, "女"}
        };
    }

    @DataProvider
    public Object[][] dataProvider2() {
        return new Object[][]{
                {"里斯", 27, "男"},
                {"小芳", 21, "女"}
        };
    }

    @DataProvider(name = "dd")
    public Object[][] dataProvider3() {
        return new Object[][]{
                {"王五", 29, "男"},
                {"小花", 24, "女"}
        };
    }
}
