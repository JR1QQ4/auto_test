package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 从 Excel 表中获取 url、type
 */
public class RestUtil {
    public static List<Rest> rests = new ArrayList<Rest>();

    static {
        ExcelUtil.load("src/main/resources/baiduInterface_v4.xlsx", "InterfaceInfo", Rest.class);
    }

    public static String getUrlByApiId(String apiId){
        for (Rest rest:
             rests) {
            if (rest.getApiId().equals(apiId)){
                return rest.getUrl();
            }
        }
        return "";
    }

    public static String getTypeByApiId(String apiId){
        for (Rest rest:
                rests) {
            if (rest.getApiId().equals(apiId)){
                return rest.getType();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        for (Rest rest:
                rests) {
            System.out.println(rest);
        }
        System.out.println(getUrlByApiId("1"));
        System.out.println(getTypeByApiId("1"));
    }
}
