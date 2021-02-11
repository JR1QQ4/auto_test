package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    /**
     * 从 Excel 表中获取数据
     *
     * @param excelPath 文件路径
     * @param sheetName sheet表名
     * @return 测试数据
     */
    public static void load(String excelPath, String sheetName) {
        Object[][] excelData = null;
        // 使用反射把数据封装
        Class clazz = Case.class;
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
                Case caseInstance = (Case) clazz.newInstance();
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
                    Method method = clazz.getMethod(methodName, String.class);
                    method.invoke(caseInstance, value);
                }
                CaseUtil.cases.add(caseInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
