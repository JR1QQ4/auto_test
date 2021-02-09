package interface_demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Person {
    private int age;
    private String fullName;
    private Calendar dateOfBirth;

    public Person(int age, String fullName, Calendar dateOfBirth) {
        this.age = age;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "age=" + age +
//                ", fullName='" + fullName + '\'' +
//                ", dateOfBirth=" + dateOfBirth.getTime() +
//                '}';
//    }
}

class PersonDemo {
    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private String name;

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public List<String> getList() {
        return list;
    }

    private String sex;
    private List<String> list;
}

public class HandleJson {
    public void parseStringToJson() {
        String params = "{“data_type”:\"all\", \"district_id\":\"222405\"}";
        Object objects = JSONObject.parse(params);
        System.out.println(objects);
        System.out.println(objects);
    }

    public static void main(String[] args) {
        // new HandleJson().parseStringToJson();

        // Calendar calendar = Calendar.getInstance();
        // calendar.set(1994, 11, 27);
        // Person person = new Person(81, "Li Xiaolong", calendar);
        // System.out.println(person.toString());

//        PersonDemo person = new PersonDemo();
//        person.setName("abcnull");
//        person.setSex("male");
//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        person.setList(list);
//        System.out.println(JSON.toJSONString(person));

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("a", 1);
//        jsonObject.put("b", "123");
//        System.out.println(jsonObject.toJSONString());
    }
}
