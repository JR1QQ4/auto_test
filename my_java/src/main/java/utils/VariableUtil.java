package utils;

import java.util.*;


/**
 * 用于处理 Excel 中的变量
 */
public class VariableUtil {
    public static Map<String, String> variableNameAndValuesMap = new HashMap<String, String>();
    public static List<Variable> variables = new ArrayList<Variable>();

    static {
        // 第一步加载表单里的数据一次酱每行封装成对象，然后统一添加到集合
        List<Variable> variableList = ExcelUtil.loadPlus(PropertiesUtil.getExcelOath(), "Variables", Variable.class);
        variables.addAll(variableList);
        // 酱变量及变量的值加载 map 集合
        loadVariableToMap();
    }

    /**
     * 遍历变量集合，将变量名和对应的变量保存到集合中
     */
    private static void loadVariableToMap() {
        for (Variable variable:
             variables) {
            String variableName = variable.getName();
            String variableValue = variable.getValue();
            variableNameAndValuesMap.put(variableName, variableValue);
        }
    }


    /**
     * 替换 Cases 表中的变量为 Variables 表中的值
     * @param parameter 需要替换的字符串
     * @return 替换后的值
     */
    public static String replaceVariables(String parameter){
        Set<String> variableNames = variableNameAndValuesMap.keySet();
        for (String variableName:
             variableNames) {
            if (parameter.contains(variableName)){
                parameter = parameter.replace(variableName, variableNameAndValuesMap.get(variableName));
            }
        }
        return parameter;
    }
}
