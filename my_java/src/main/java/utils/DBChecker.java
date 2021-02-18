package utils;

/**
 * 用于存放 sql 语句，方便转换为 json 对象
 */
public class DBChecker {
    private String no;  // 保存序号
    private String sql;  // 保存 sql 语句

    public String getNo() {
        return no;
    }

    public String getSql() {
        return sql;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public DBChecker(String no, String sql) {
        this.no = no;
        this.sql = sql;
    }

    public DBChecker() {
    }

    @Override
    public String toString() {
        return "DBChecker{" +
                "no='" + no + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
