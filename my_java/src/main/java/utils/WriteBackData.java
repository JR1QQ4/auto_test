package utils;

/**
 * 用于存放回写数据，避免平频繁读写文件
 */
public class WriteBackData {
    private String sheetName;  // 表单名
    private String caseId;
    private String columnName;  // 单元格所属列的列名
    private String result;  // 需要回写的数据

    public String getSheetName() {
        return sheetName;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getResult() {
        return result;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public WriteBackData(String sheetName, String caseId, String columnName, String result) {
        this.sheetName = sheetName;
        this.caseId = caseId;
        this.columnName = columnName;
        this.result = result;
    }

    public WriteBackData() {
    }

    @Override
    public String toString() {
        return "WriteBackData{" +
                "sheetName='" + sheetName + '\'' +
                ", caseId='" + caseId + '\'' +
                ", columnName='" + columnName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
