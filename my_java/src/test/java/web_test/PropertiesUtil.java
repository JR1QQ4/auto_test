package web_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class PropertiesUtil {
    public static Properties properties = new Properties();

    static {
        try{
            InputStream inputStream = new FileInputStream(new File("src/main/resources/config.properties"));
            properties.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getRegisterExcelPath(){
        return properties.getProperty("register.excel.path");
    }

    public static String getLoginExcelPath(){
        return properties.getProperty("login.excel.path");
    }

    public static String getUILibraryPath(){
        return properties.getProperty("UILibrary.path");
    }

    public static String getRegisterUrl(){
        return properties.getProperty("register.url");
    }

    public static String getLoginUrl(){
        return properties.getProperty("login.url");
    }

    public static void main(String[] args) {
        System.out.println(getRegisterExcelPath());
        System.out.println(getLoginExcelPath());
    }
}
