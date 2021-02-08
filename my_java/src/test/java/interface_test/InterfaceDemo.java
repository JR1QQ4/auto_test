package interface_test;

import interface_demo.Demo;
import interface_demo.HandleExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class InterfaceDemo {
    @Test(dataProvider = "getTestData")
    public void testBaiduInterface(String http_type, String data_type, String district_id) {
        String key = "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7";
        String url = "http://api.map.baidu.com/weather/v1/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("ak", key);
        params.put("data_type", data_type);
        params.put("district_id", district_id);

        String result = "";

        if (http_type.contains("get")) {
            result = Demo.handleGet(url, params);
            System.out.println("get");
        } else if (http_type.contains("post")) {
            result = Demo.handlePost(url, params);
            System.out.println("post");
        }
        System.out.println(result);
    }

    @DataProvider
    public Object[][] getTestData() {
        String excelPath = "src/main/resources/baiduInterface.xlsx";
        String sheetName = "Interface";
        int starRow = 1;
        int endRow = 6;
        int startColumn = 4;
        int endColumn = 6;
        return HandleExcel.getDataFromExcel(excelPath, sheetName, starRow, endRow, startColumn, endColumn);
    }
}
