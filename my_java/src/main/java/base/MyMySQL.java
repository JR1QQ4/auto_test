package base;

import java.io.*;
import java.sql.*;
import java.util.*;

public class MyMySQL {
    public static Properties properties = new Properties();

    static {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/jdbc.properties"));
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String sql1 = "select * from city limit 5";
        String sql2 = "select Name,CountryCode from city limit ?";
        String sql3 = "select * from city where population > ? limit ?";

        List<Map<String, Object>> columnLabelAndValues = MyMySQL.query(sql3, 500000, 3);
        // queryWithStatement(sql1);

        for (Map<String, Object> columnLabelAndValue :
                columnLabelAndValues) {
            Set<String> labels = columnLabelAndValue.keySet();
            for (String label :
                    labels) {
                System.out.println(label + ": " + columnLabelAndValue.get(label));
            }
            System.out.println("--------------------------------------");
        }
    }

    public static List<Map<String, Object>> query(String sql, Object... values) {
        List<Map<String, Object>> columnLabelAndValues = new ArrayList<Map<String, Object>>();
        try {
            // 1.连接数据库
            Connection connection = handleConnection();

            // 2.获取 PreparedStatement 对象（此类型的对象提供数据库操作方法）
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 3.设置条件字段的值
            for (int i = 0; i < values.length; i++) {
                // parameterIndex 表示第几个 ?(占位符)，从 1 开始，values 表示替换的值
                preparedStatement.setObject(i + 1, values[i]);
            }

            // 4.调用查询方法，执行查询，返回 ResultSet(结果集)
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获取查询相关的信息
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 得到查询字段的数目
            int columnCount = resultSetMetaData.getColumnCount();

            // 5.从结果集取出查询数据
            while (resultSet.next()) {
                Map<String, Object> columnLabelAndValue = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    // 获取列名，然后取出数据
                    String columnLable = resultSetMetaData.getColumnLabel(i);
                    String columnValue = resultSet.getObject(columnLable).toString();
                    columnLabelAndValue.put(columnLable, columnValue);
                    // System.out.println(columnLable + ": " + columnValue);
                }
                columnLabelAndValues.add(columnLabelAndValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnLabelAndValues;
    }

    public static void queryWithStatement(String sql, Object... values) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = handleConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                // System.out.println(name);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // System.out.println("------------ queryWithStatement END -------------");
    }

    public static Connection handleConnection() throws SQLException {
        // 从 properties 文件中读取数据
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        // 1. 建立连接
        return DriverManager.getConnection(url, username, password);
    }
}

class CountryPopulation {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public CountryPopulation(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}