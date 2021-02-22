package interface_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.util.Map;

public class InterfaceTestV2 {
    @DataProvider(name = "data")
    public Object[][] getData() {
        String[] columnNames = {"CaseId", "ApiId", "Params"};
        return CaseUtil.getCasesDatesApiId("1", columnNames);
    }

    @Test(dataProvider = "data")
    public void testBaiduWeather1(String caseId, String apiId, String parameters) {
        System.out.println("----------------");
        String type = RestUtil.getTypeByApiId(apiId);
        String url = RestUtil.getUrlByApiId(apiId);

//        JSONObject jsonObject = JSON.parseObject(parameters);
//        Map<String, String> params1 =JSONObject.toJavaObject(jsonObject, Map.class);

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        params.put("ak", "");
        String result = HttpUtil.handlePostAndGet(type, url, params);
        System.out.println(result);

        // 重复读写文件
//        ExcelUtil.writeBackData("src/main/resources/baiduInterface_v2.xlsx", "Cases", caseId,
//                "ActualResponseData", result);

        // 使用类保存需要回写的数据
        WriteBackData writeBackData = new WriteBackData("Cases", caseId, "ActualResponseData", result);
        ExcelUtil.writeBackDataList.add(writeBackData);
    }
}
