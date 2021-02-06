package base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflectDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1.获取字节码
        Class<MyCDCatalog> cd = MyCDCatalog.class;
        // 2.创建对象
        MyCDCatalog myCDCatalog = cd.newInstance();
        // 3.获取方法
        Method method1 = cd.getMethod("setTITLE", String.class);
        Method method2 = cd.getMethod("getTITLE");
        // 4.调用
        method1.invoke(myCDCatalog, "Empire Burlesque");
        System.out.println(method2.invoke(myCDCatalog));
    }
}
