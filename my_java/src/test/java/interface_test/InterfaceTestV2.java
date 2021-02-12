package interface_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CaseUtil;
import utils.HttpUtil;
import utils.RestUtil;

import java.util.Map;

public class InterfaceTestV2 {
    @DataProvider(name = "data")
    public Object[][] getData() {
        String[] columnNames = {"ApiId", "Params"};
        return CaseUtil.getCasesDatesApiId("1", columnNames);
    }
    @Test(dataProvider = "data")
    public void testBaiduWeather1(String apiId, String parameters){
        System.out.println("----------------");
        String type = RestUtil.getTypeByApiId(apiId);
        String url = RestUtil.getUrlByApiId(apiId);

//        JSONObject jsonObject = JSON.parseObject(parameters);
//        Map<String, String> params1 =JSONObject.toJavaObject(jsonObject, Map.class);

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        params.put("ak", "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7");
        String result = HttpUtil.handlePostAndGet(type, url, params);
        System.out.println(result);
    }
}
