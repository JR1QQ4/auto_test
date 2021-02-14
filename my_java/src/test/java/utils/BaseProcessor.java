package utils;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 接口测试统一处理类，抽取了公共方法
 */
public class BaseProcessor {
    @Test(dataProvider = "data")
    public void testBaiduWeather1(String caseId, String apiId, String parameters) {
        System.out.println("----------------");
        String type = RestUtil.getTypeByApiId(apiId);
        String url = RestUtil.getUrlByApiId(apiId);

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        params.put("ak", "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7");
        String result = HttpUtil.handlePostAndGet(type, url, params);
        System.out.println(result);

        // 重复读写文件
//        ExcelUtil.writeBackData("src/main/resources/baiduInterface_v2.xlsx", "Cases", caseId,
//                "ActualResponseData", result);

        // 使用类保存需要回写的数据
         ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "ActualResponseData", result));
    }
}
