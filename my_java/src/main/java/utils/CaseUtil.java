package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用例工具类
 */
public class CaseUtil {
    static {
//        ExcelUtil.getDataFromExcel()
    }

    // 保存所有的测试用例数据
    public static List<Case> cases = new ArrayList<Case>();

    /**
     * 通过 apiId 和 列名 获取测试数据
     * @param apiId 指定接口编号
     * @param columns 腰获取的数据对应的列名
     * @return 测试数据
     */
    public static Object[][] getCasesDatesApiId(String apiId, String[] columns){
        // 遍历数据，找到指定接口编号对应的测试数据
//        for (Case case:
//             cases) {
//
//        }
        return null;
    }
}
