package utils;

import org.testng.Assert;

/**
 * 处理结果信息
 */
public class AssertUtil {

    public static String assertEquals(String expectedResponseData, String actualResponseData) {
        String result = "通过";
        try{
            Assert.assertEquals(expectedResponseData, actualResponseData);
            System.out.println(expectedResponseData + ", " + actualResponseData);
        }catch (Throwable e){
            result = actualResponseData;
        }
        return result;
    }
}
