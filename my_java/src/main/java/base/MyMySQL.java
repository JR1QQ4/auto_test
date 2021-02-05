package base;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public static void main(String[] args) throws SQLException {
        String url = "";
        String user = "";
        String password = "";

        // 1.根据连接信息，获得数据库连接（连上数据库）
        Connection connection = DriverManager.getConnection(url, user, password);
    }

    public static void query(String sql, Object ... values){
        try{
            System.out.println("开始解析 properties 文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // 从 properties 文件中读取数据
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        // 1. 建立连接
        return DriverManager.getConnection(url, username, password);
    }
}
