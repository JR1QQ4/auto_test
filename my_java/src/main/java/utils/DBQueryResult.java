package utils;

import java.util.Map;

/**
 * 用于保存从数据库中查询到的结果
 */
public class DBQueryResult {
    private String no;
    private Map<String, Object> columnLabelAndValues;  // key保存的是字段名，value保存的是对应字段查到的值

    public String getNo() {
        return no;
    }

    public Map<String, Object> getColumnLabelAndValues() {
        return columnLabelAndValues;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setColumnLabelAndValues(Map<String, Object> columnLabelAndValues) {
        this.columnLabelAndValues = columnLabelAndValues;
    }

    public DBQueryResult(String no, Map<String, Object> columnLabelAndValues) {
        this.no = no;
        this.columnLabelAndValues = columnLabelAndValues;
    }

    public DBQueryResult() {
    }

    @Override
    public String toString() {
        return "DBQueryResult{" +
                "no='" + no + '\'' +
                ", columnLabelAndValues=" + columnLabelAndValues +
                '}';
    }
}
