package base;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectStudent {
    public static String parseStringToUpper(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
    public static List<Student> parseXML(String filePath) {
        // 使用 dom4j 解析 XML 步骤:
        // 1.准备解析器
        SAXReader saxReader = new SAXReader();
        List<Student> studentList = new ArrayList<Student>();
        try{
            // 2.获取 Document 对象
            Document document = saxReader.read(filePath);
            // 3.获取根节点
            Element root = document.getRootElement();
            // 4.根据根节点遍历子节点
            List<Element> studentElements = root.elements("student");
            // 使用反射处理解析到的数据，步骤:
            // a.获取 Student 类的字节码
            Class<Student> clazz = Student.class;
            Class<Student> clazzConstructor = Student.class;
            for (Element studentElement:
                    studentElements) {
                // b.通过字节码创建实例
                Student student = clazz.newInstance();
                List<Element> studentChildElements = studentElement.elements();

                System.out.println(studentElement.attributes());

                for (Element studentChildElement:
                        studentChildElements) {
                    String elementName = studentChildElement.getName();
                    String value = studentChildElement.getText();
                    String methodName = "set" + ReflectStudent.parseStringToUpper(elementName);
                    // c.获取方法
                    Method method = clazz.getMethod(methodName, String.class);
                    // d.调用方法
                    method.invoke(student, value);
                }
                studentList.add(student);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/student.xml";
        List<Student> studentList = ReflectStudent.parseXML(filePath);

        for (Student stu :
                studentList) {
            System.out.println(stu.toString());
        }
    }
}
