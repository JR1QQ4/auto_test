package interface_demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class HandleExcel {
    public static Map<String, List<String>> getDataFromExcelDemo() {
        String excelPath = "src/main/resources/baiduInterface.xlsx";
        Map<String, List<String>> excelMapData = new HashMap<String, List<String>>();
        try {
            InputStream inputStream = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet hssfSheet = workbook.getSheetAt(0);
            for (Row row :
                    hssfSheet) {
                List<String> stringList = new ArrayList<String>();
                if (row.getRowNum() == 0) {
                    continue;
                }
                String areaNum = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                Number postcode = row.getCell(4).getNumericCellValue();

                stringList.add(areaNum);
                stringList.add(province);
                stringList.add(city);
                stringList.add(district);
                stringList.add(postcode.toString());

                excelMapData.put("'" + row.getRowNum() + "'", stringList);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelMapData;
    }

    /**
     * 获取 Excel 数据，仅支持连续的区间
     * @param excelPath   Excel 文件路径
     * @param sheetName   Sheet 名称
     * @param startRow    开始的索引值
     * @param endRow      结束的索引值
     * @param startColumn 开始的索引值
     * @param endColumn   结束的索引值
     * @return Object[][]
     */
    public static Object[][] getDataFromExcel(String excelPath, String sheetName, int startRow, int endRow, int startColumn, int endColumn) {
        // String excelPath = "src/main/resources/baiduInterface.xlsx";
        Object[][] excelData = null;
        try {
            // 1.获取工作簿
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            // 2.获取 Sheet 表
            Sheet sheet = workbook.getSheet(sheetName);
            // 3.遍历行和列，获取数据
            excelData = new Object[endRow - startRow + 1][endColumn - startColumn + 1];
            for (int i = startRow; i <= endRow; i++) {
                Row row = sheet.getRow(i);
                for (int j = startColumn; j <= endColumn; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = "";
                    cell.setCellType(CellType.STRING);
                    value = cell.getStringCellValue();

                    // CellType cellType = cell.getCellTypeEnum();
                    // switch (cellType) {
                    //     case NUMERIC:
                    //         value = String.valueOf((int)cell.getNumericCellValue());
                    //         break;
                    //     case STRING:
                    //         value = cell.getStringCellValue();
                    //         break;
                    //     case FORMULA:
                    //     case BLANK:
                    //     case BOOLEAN:
                    //     case ERROR:
                    //         break;
                    // }

                    excelData[i - startRow][j - startColumn] = value;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelData;
    }

    /**
     * 获取 Excel 中的数据，支持不连续的范围
     * @param excelPath 文件路径
     * @param sheetName 表名
     * @param rows 行号索引
     * @param columns 列号索引
     * @return Object[][]
     */
    public static Object[][] getDataFromExcel(String excelPath, String sheetName, int [] rows, int [] columns) {
        Object[][] excelData = null;
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            Sheet sheet = workbook.getSheet(sheetName);
            excelData = new Object[rows.length][columns.length];
            for (int i = 0; i < rows.length; i++) {
                Row row = sheet.getRow(rows[i]);
                for (int j = 0; j < columns.length; j++) {
                    Cell cell = row.getCell(columns[j], Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = "";
                    cell.setCellType(CellType.STRING);
                    value = cell.getStringCellValue();
                    excelData[i][j] = value;
                    System.out.println(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelData;
    }

    public static void main(String[] args) {
//        Map<String, List<String>> stringListMap = HandleExcel.getDataFromExcelDemo();
//        Set<String> keys = stringListMap.keySet();
//        for (String key :
//                keys) {
//            List<String> value = stringListMap.get(key);
//            System.out.println(key + ": " + value);
//        }

//        String excelPath = "src/main/resources/baiduInterface.xlsx";
//        String sheetName = "Interface";
//        int starRow = 1;
//        int endRow = 6;
//        int startColumn = 5;
//        int endColumn = 6;
//        Object[][] datas = HandleExcel.getDataFromExcel(excelPath, sheetName, starRow, endRow, startColumn, endColumn);
//        for (int i = 0; i < datas.length; i++) {
//            for (int j = 0; j < datas[i].length; j++) {
//                System.out.print(datas[i][j]);
//            }
//            System.out.println();
//        }

        // String excelPath = "src/main/resources/baiduInterface.xlsx";
        // String sheetName = "Interface";
        // int[] rows = {1, 3, 4};
        // int[] columns = {5, 6, 3};
        // getDataFromExcel(excelPath, sheetName, rows, columns);
    }
}
