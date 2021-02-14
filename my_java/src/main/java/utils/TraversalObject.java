package utils;

import java.util.Map;
import java.util.Set;

/**
 * 遍历对象工具类
 */
public class TraversalObject {
    public static <T> void traversalMap(Map<T, T> params) {
        Set<T> keys = params.keySet();
        for (T key :
                keys) {
            T value = params.get(key);
            System.out.println(key + ":" + value);
        }
    }
    public static <T, S> void traversalMapString(Map<T, S> params) {
        Set<T> keys = params.keySet();
        for (T key :
                keys) {
            S value = params.get(key);
            System.out.println(key + ":" + value);
        }
    }
}
