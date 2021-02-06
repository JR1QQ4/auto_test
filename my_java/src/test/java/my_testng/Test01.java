package my_testng;

import org.testng.Assert;
import org.testng.annotations.*;

public class Test01 {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Test01.beforeSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.print("Test01.beforeTest");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.print("Test01.beforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.print("Test01.beforeMethod");
    }

    @Test
    public void testCalculatorWithTwoNumber() {
        System.out.println("Test01.testCalculatorWithTwoNumber");
        int actual = new Calculator().add(3, 4);
        int expected = 7;
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.print("Test01.afterMethod");
    }

    @AfterClass
    public void afterClass() {
        System.out.print("Test01.afterClass");
    }

    @AfterTest
    public void afterTest() {
        System.out.print("Test01.afterTest");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Test01.afterSuite");
    }
}
