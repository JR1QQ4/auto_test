package my_testng;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test02 {
    @BeforeMethod
    public void setUp() {
        System.out.println("Test02.setUp");
    }

    @Test
    public void testCalculatorWithTwoNumber() {
        System.out.println("Test02.testCalculatorWithTwoNumber");
        int actual = new Calculator().add(3, 4);
        int expected = 6;
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Test02.tearDown");
    }
}
