package web_test;

import org.apache.poi.ss.usermodel.*;
import utils.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    /**
     * 从 Excel 中读取数据
     * @param excelPath Excel路径
     * @param sheetName 表名
     * @param clazz 类的字节码
     * @param <T> 泛型
     * @return 泛型集合
     */
    public static <T> List<T> loadPlus(String excelPath, String sheetName, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            // 创建 Workbook 对象
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            // 创建 Sheet 对象
            Sheet sheet = workbook.getSheet(sheetName);
            // 处理首行的标题
            Row titleRow = sheet.getRow(0);
            int lastCellNum = titleRow.getLastCellNum();
            String[] titleArray = new String[lastCellNum];
            for (int i = 0; i < lastCellNum; i++) {
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String value = cell.getStringCellValue();
                String title = value.substring(0, value.indexOf("("));
                titleArray[i] = title;
            }
            // 处理标题行以下的测试数据
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                T obj = clazz.newInstance();
                Row dataRow = sheet.getRow(i);
                if (dataRow == null || isEmptyRow(dataRow)) {
                    continue;
                }
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    // 反射
                    String methodName = "set" + titleArray[j];
                    try {
                        Method method = clazz.getMethod(methodName, String.class);
                        method.invoke(obj, value);
                    }catch (Exception e) {
                        System.out.println("没有此方法: " + methodName);
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断是不是空行
     *
     * @param dataRow Row行对象
     * @return 是空行返回 true，不是空行返回 false
     */
    private static boolean isEmptyRow(Row dataRow) {
        int lastCellNum = dataRow.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue();
            if (value != null && value.trim().length() > 0) {
                return false;
            }
        }
        return true;
    }
}
