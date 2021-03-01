package utils;

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

    public static String getExcelOath(){
        return properties.getProperty("excel.path");
    }

    public static String getApiContentType(){
        return properties.getProperty("api.content.type");
    }

    public static void main(String[] args) {
        System.out.println(getExcelOath());
        System.out.println(getApiContentType());
    }
}
