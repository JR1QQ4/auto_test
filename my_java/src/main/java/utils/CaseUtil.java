package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 用例工具类
 */
public class CaseUtil {
    // 保存所有的测试用例数据
    public static List<Case> cases = new ArrayList<Case>();

    static {
        ExcelUtil.load("src/main/resources/baiduInterface_v2.xlsx", "Cases", Case.class);
    }

    /**
     * 通过 apiId 和 列名 获取测试数据
     *
     * @param apiId       指定接口编号
     * @param columnNames 需要获取的数据对应的列名数组
     * @return 测试数据
     */
    public static Object[][] getCasesDatesApiId(String apiId, String[] columnNames) {
        // 保存指定接口编号的 Case 对象
        ArrayList<Case> caseArrayList = new ArrayList<Case>();
        // 遍历数据，找到指定接口编号对应的测试数据
        for (Case cs :
                cases) {
            String apiIdFromCases = cs.getApiId();
            if (apiIdFromCases.equals(apiId)) {
                caseArrayList.add(cs);
            }
        }
        Object[][] casesData = new Object[caseArrayList.size()][columnNames.length];
        try {
            int caseListSize = caseArrayList.size();
            int namesLength = columnNames.length;
            Class<Case> clazz = Case.class;
            for (int i = 0; i < caseListSize; i++) {
                Case cs = caseArrayList.get(i);
                for (int j = 0; j < namesLength; j++) {
                    String columnName = columnNames[j];
                    String methodName = "get" + columnName;
                    Method method = clazz.getMethod(methodName);
                    // 要反射的对象，cs 本来就是 Case 对象
                    String value = (String) method.invoke(cs);
                    casesData[i][j] = value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return casesData;
    }

    public static void main(String[] args) {
        String apiId = "1";
        String[] columnNames = {"ApiId", "Params"};
        Object[][] objects = getCasesDatesApiId(apiId, columnNames);

        for (Object[]  objects1:
                objects) {
            for (Object object:
                 objects1) {
                System.out.print("["+ object +"]");
            }
            System.out.println();
        }
    }
}
