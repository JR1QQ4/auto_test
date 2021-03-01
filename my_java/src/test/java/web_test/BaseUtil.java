package web_test;

import java.lang.reflect.Method;
import java.util.List;

public class BaseUtil {
    public static <T> Object[][] getObjects(String[] columnNames, Class<T> clazz, List<Object> satisfied) {
        Object[][] data = new Object[satisfied.size()][columnNames.length];
        for (int i = 0; i < satisfied.size(); i++) {
            Object objectData = satisfied.get(i);
            for (int j = 0; j < columnNames.length; j++) {
                String methodName = "get" + columnNames[j];
                Method method;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(objectData);
                    data[i][j] = value;
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
