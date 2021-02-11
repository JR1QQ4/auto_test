package interface_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CaseUtil;
import utils.HttpUtil;

import java.util.Map;

public class InterfaceTestV2 {
    @DataProvider(name = "data")
    public Object[][] getData() {
        String[] columnNames = {"Params"};
        return CaseUtil.getCasesDatesApiId("1", columnNames);
    }
    @Test(dataProvider = "data")
    public void testBaiduWeather1(String parameters){
        System.out.println("----------------");
        String sendType = "";
        String url = "";
        JSONObject jsonObject = JSON.parseObject(parameters);
        Map<String, String> params1 =JSONObject.toJavaObject(jsonObject, Map.class);
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        String result = HttpUtil.handlePostAndGet(sendType, url, params);
        System.out.println(result);
    }
}
