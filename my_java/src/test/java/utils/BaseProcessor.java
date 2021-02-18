package utils;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 接口测试统一处理类，抽取了公共方法
 */
public class BaseProcessor {
    public static String[] columnNames = {"CaseId", "ApiId", "Params", "ExpectedResponseData", "BeforeValidateSql", "AfterValidateSql"};

    @Test(dataProvider = "data")
    public void testBaiduWeather1(String caseId, String apiId, String parameters, String expectedResponseData, String beforeValidateSql, String afterValidateSql) {
        System.out.println("----------------");
        System.out.println(beforeValidateSql + ", " + afterValidateSql);

        if (beforeValidateSql != null && beforeValidateSql.trim().length() > 0){
            // 传入了 sql 脚本，在接口调用前执行数据验证
            String beforeValidateResult = DBHandleUtil.doQuery(beforeValidateSql);
            ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "BeforeValidateResult", beforeValidateResult));
        }

        String type = RestUtil.getTypeByApiId(apiId);
        String url = RestUtil.getUrlByApiId(apiId);

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        params.put("ak", "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7");
        String actualResponseData = HttpUtil.handlePostAndGet(type, url, params);
        System.out.println(actualResponseData);

        actualResponseData = AssertUtil.assertEquals(expectedResponseData, actualResponseData);

        // 重复读写文件
//        ExcelUtil.writeBackData("src/main/resources/baiduInterface_v2.xlsx", "Cases", caseId,
//                "ActualResponseData", result);

        // 使用类保存需要回写的数据
         ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "ActualResponseData", actualResponseData));

        if (afterValidateSql != null && afterValidateSql.trim().length() > 0){
            // 传入了 sql 脚本，在接口调用后执行数据验证
            String afterValidateResult = DBHandleUtil.doQuery(afterValidateSql);
            ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "AfterValidateResult", afterValidateResult));
        }
    }
}
