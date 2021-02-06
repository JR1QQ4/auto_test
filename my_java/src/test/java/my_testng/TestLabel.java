package my_testng;

import org.testng.annotations.Test;

public class TestLabel {
    @Test(dependsOnMethods = "test2", groups = "g1")
    public void test1() {
        System.out.println("TestLabel.test1()");
    }

    @Test
    public void test2() {
        System.out.println("TestLabel.test2()");
    }

    @Test(groups = "g1")
    public void test3() {
        System.out.println("TestLabel.test3()");
    }

    @Test(enabled = false)
    public void test4() {
        System.out.println("TestLabel.test4()");
    }

    @Test(timeOut = 1, groups = "g1")
    public void test5() {
        for (int i = 0; i < 100000; i++) {
            System.out.print(i);
        }
        System.out.println("TestLabel.test5()");
    }
}
