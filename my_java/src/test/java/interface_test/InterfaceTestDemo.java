package interface_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import interface_demo.Demo;
import interface_demo.HandleExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InterfaceTestDemo {
    /**
     * 通过拿到的 apiId 去接口页查找接口地址，并发送请求
     *
     * @param apiIdFromCase  接口ID
     * @param paramsFromCase 接口参数
     */
    @Test(dataProvider = "data")
    public void test1(String apiIdFromCase, String paramsFromCase) {
        // System.out.println(apiIdFromCase + ": " + params);

        int[] rows = {1, 2, 3, 4, 5, 6};
        int[] columns = {0, 2, 3};
        Object[][] interfaceData = HandleExcel.getDataFromExcel("src/main/resources/baiduInterface_v2.xlsx",
                "InterfaceInfo", rows, columns);

        String url = "";
        String sendType = "";

        for (Object[] objects :
                interfaceData) {
            String apiIdFromInterface = objects[0].toString();
            if (apiIdFromInterface.equals(apiIdFromCase)) {
                sendType = objects[1].toString();
                url = objects[2].toString();
                break;
            }
        }
        // 发送请求
        // Map<String ,String > params = (Map<String ,String >)JSONObject.parse(paramsFromCase);
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = JSON.parseObject(paramsFromCase);

        jsonObject.put("ak", "");
        Set<String> keys = jsonObject.keySet();
        for (String key :
                keys) {
            String value = jsonObject.getString(key);
            params.put(key, value);
        }

        System.out.println(Demo.handlePostAndGet(sendType, url, params));
    }

    /**
     * 获取测试用例
     *
     * @return 返回 apiId 和 params 的数组
     */
    @DataProvider
    public Object[][] data() {
        Object[][] casesData = null;
        int[] rows = {1, 2, 3};
        int[] columns = {2, 3};
        casesData = HandleExcel.getDataFromExcel("src/main/resources/baiduInterface_v2.xlsx",
                "Cases", rows, columns);
        return casesData;
    }
}
