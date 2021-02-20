package utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 接口测试统一处理类，抽取了公共方法
 */
public class BaseProcessor {
    private Logger logger = Logger.getLogger(BaseProcessor.class);

    public static String[] columnNames = {"CaseId", "ApiId", "Params", "ExpectedResponseData", "BeforeValidateSql", "AfterValidateSql"};

    @Test(dataProvider = "data")
    public void testBaiduWeather1(String caseId, String apiId, String parameter, String expectedResponseData, String beforeValidateSql, String afterValidateSql) {
        // System.out.println("----------------");

        if (beforeValidateSql != null && beforeValidateSql.trim().length() > 0) {
            logger.info("调用接口前的数据验证");
            // 替换变量
            beforeValidateSql = VariableUtil.replaceVariables(beforeValidateSql);

            // System.out.println("beforeValidateSql: " + beforeValidateSql);

            // 传入了 sql 脚本，在接口调用前执行数据验证
            String beforeValidateResult = DBHandleUtil.doQuery(beforeValidateSql);
            ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "BeforeValidateResult", beforeValidateResult));
        }

        String type = RestUtil.getTypeByApiId(apiId);
        logger.info("根据接口编号【" + apiId + "】获取接口请求类型【" + type + "】");
        String url = RestUtil.getUrlByApiId(apiId);
        logger.info("根据接口编号【" + apiId + "】获取接口请求地址【" + url + "】");

        // 替换变量
        logger.info("替换前变量值: " + parameter);
        parameter = VariableUtil.replaceVariables(parameter);
        logger.info("替换后变量值: " + parameter);

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameter);
        params.put("ak", "7tzUh9u1Pb53DY5yS6Wy5W0Kb2YSXMs7");
        logger.info("开始调用接口");
        String actualResponseData = HttpUtil.handlePostAndGet(type, url, params);

        logger.info("比较期望值【" + expectedResponseData + "】和实际值【" + actualResponseData + "】");
        actualResponseData = AssertUtil.assertEquals(expectedResponseData, actualResponseData);

        // 重复读写文件
//        ExcelUtil.writeBackData("src/main/resources/baiduInterface_v2.xlsx", "Cases", caseId,
//                "ActualResponseData", result);

        // 使用类保存需要回写的数据
        ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "ActualResponseData", actualResponseData));

        if (afterValidateSql != null && afterValidateSql.trim().length() > 0) {
            logger.info("调用接口后的数据验证");
            // 替换变量
            afterValidateSql = VariableUtil.replaceVariables(afterValidateSql);
            System.out.println("afterValidateSql: " + afterValidateSql);

            // 传入了 sql 脚本，在接口调用后执行数据验证
            String afterValidateResult = DBHandleUtil.doQuery(afterValidateSql);
            ExcelUtil.writeBackDataList.add(new WriteBackData("Cases", caseId, "AfterValidateResult", afterValidateResult));
        }
    }
}
