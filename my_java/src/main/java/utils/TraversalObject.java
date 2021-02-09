package utils;

import java.util.Map;
import java.util.Set;

public class TraversalObject {
    public static <T> void traversalMap(Map<T, T> params) {
        Set<T> keys = params.keySet();
        for (T key :
                keys) {
            T value = params.get(key);
            System.out.println(key + ":" + value);
        }
    }
}
