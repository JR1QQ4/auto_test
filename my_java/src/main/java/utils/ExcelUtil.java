package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static Map<String, Integer> caseIdRowNumMapping = new HashMap<String, Integer>();
    public static Map<String, Integer> columnNameColumnNumMapping = new HashMap<String, Integer>();
    public static List<WriteBackData> writeBackDataList = new ArrayList<WriteBackData>();

    static {
        loadRowNumAndColumnNumMapping("src/main/resources/baiduInterface_v2.xlsx", "Cases");
    }

    /**
     * 获取 caseId 对应的行索引和 column 对应的列索引
     *
     * @param excelPath Excel 文件
     * @param sheetName sheet 表名
     */
    private static void loadRowNumAndColumnNumMapping(String excelPath, String sheetName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row titleRow = sheet.getRow(0);
            // 循环处理第一行标题行
            if (titleRow != null && !isEmptyRow(titleRow)) {
                int lastColumnNum = titleRow.getLastCellNum();
                for (int i = 0; i < lastColumnNum; i++) {
                    Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String title = cell.getStringCellValue();
                    int columnNum = cell.getAddress().getColumn();
                    columnNameColumnNumMapping.put(title.substring(0, title.indexOf("(")), columnNum);
                }
            }
            // 从第二行开始，获取所有的数据行
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row dataRow = sheet.getRow(i);
                Cell firstColumnOfRow = dataRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                firstColumnOfRow.setCellType(CellType.STRING);
                String caseId = firstColumnOfRow.getStringCellValue();
                int rowNum = dataRow.getRowNum();
                caseIdRowNumMapping.put(caseId, rowNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从 Excel 表中获取数据
     *
     * @param excelPath 文件路径
     * @param sheetName sheet表名
     * @param clazz     传入类的字节码
     */
    public static <T> void load(String excelPath, String sheetName, Class<T> clazz) {
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
                Object obj = clazz.newInstance();
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
                    method.invoke(obj, value);
                }
                if (obj instanceof Case) {
                    Case cs = (Case) obj;
                    CaseUtil.cases.add(cs);
                } else if (obj instanceof Rest) {
                    Rest rest = (Rest) obj;
                    RestUtil.rests.add(rest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * 回写数据到 Excel
     *
     * @param excelPath  文件路径
     * @param sheetName  表名
     * @param caseId     用例编号
     * @param columnName 列名
     * @param result     响应结果
     */
    public static void writeBackData(String excelPath, String sheetName, String caseId, String columnName, String result) {
        int rowNum = caseIdRowNumMapping.get(caseId);
        int columnNum = columnNameColumnNumMapping.get(columnName);
//        System.out.println(columnNum);
//        System.out.println(rowNum);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(columnNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(result);
            outputStream = new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 批量回写数据
     *
     * @param excelPath Excel文件路径
     */
    public static void batchWriteBackData(String excelPath) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            for (WriteBackData writeBackData :
                    writeBackDataList) {
                String sheetName = writeBackData.getSheetName();
                String caseId = writeBackData.getCaseId();
                String columnName = writeBackData.getColumnName();
                String result = writeBackData.getResult();

//                System.out.println(writeBackData.toString());

                Sheet sheet = workbook.getSheet(sheetName);
                int rowNum = caseIdRowNumMapping.get(caseId);
                int columnNum = columnNameColumnNumMapping.get(columnName);
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(columnNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(result);
            }
            outputStream = new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TraversalObject.traversalMapString(columnNameColumnNumMapping);
        writeBackData("src/main/resources/baiduInterface_v2.xlsx", "Cases", "1", "ActualResponseData", "result");
    }
}
