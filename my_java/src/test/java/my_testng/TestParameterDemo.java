package my_testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameterDemo {
    @Test
    @Parameters(value = {"browserName"})
    public static void testParameter1(String browserName) {
        System.out.println("browserName=" + browserName);
    }

    @Test
    @Parameters(value = {"browserName", "browserVersion"})
    public static void testParameter2(String browserName, String browserVersion) {
        System.out.println("browserName=" + browserName + ", browserVersion=" + browserVersion);
    }
}
