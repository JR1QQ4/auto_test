package web_test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    /**
     * 正则匹配获取字符串中的指定值
     * @param regex 正则表达式
     * @param input 指定字符串
     * @return 匹配的字符串
     */
    public static String getMatchedString(String regex, String input){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher.group(0);
        }else {
            return "";
        }
    }

    public static void main(String[] args) {
        String regex = "共 \\d+ 条";
        String input = "显示 1 到 9，共 9 条";
        System.out.println(getMatchedString(regex, input));
        input = getMatchedString(regex, input);
        regex = "\\d+";
        System.out.println(getMatchedString(regex, input));
    }
}
