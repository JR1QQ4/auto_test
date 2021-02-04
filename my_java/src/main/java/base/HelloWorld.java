package base;

import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        boolean aa = true;
        byte bb = 127;
        short cc = 32767;
        int dd = 2147483647;
        long ee = 9223372036854775807l;
        float ff = 3.14f;
        double gg = 3.14;
        char hh = 'a';

        String name = "王尼玛";
        int age = 25;
        // System.out.println(name + age);

        int[] arr1 = new int[5];
        String[] names = {"张三", "李四", "王五"};
        arr1[0] = 60;
        arr1[1] = 70;
        arr1[2] = 80;
        arr1[3] = 90;
        arr1[4] = 100;
        // for (String n :
        //         names) {
        //     System.out.println(n);
        // }
        // for (int i = 0; i < arr1.length; i++) {
        //     System.out.println(arr1[i]);
        // }

        String[][] names2 = {{"张三", "王尼玛"}, {"里斯", "李小华", "abc"}};
        String[][] arr2 = new String[4][3];
        arr2[1][1] = "岁月静好";
        // System.out.println(names2.length);
        // System.out.println(arr2.length);
        // System.out.println(arr2[1][0]);
        // System.out.println(arr2[1][1]);
    }
}

/**
 * 运算符
 */
class MyOperator {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;

        // System.out.println(a + b);
        // System.out.println(a - b);
        // System.out.println(a * b);
        // System.out.println(a / b);  // 0
        // System.out.println(a % b);

        // System.out.println(a++);  // 1
        // System.out.println(++a);  // 3

        // System.out.println((a > b) & (a + b < c));
        // System.out.println((a > b) && (++b < c));
        // System.out.println(b);  // 2
        // System.out.println((a < b) | (b < c));
        // System.out.println((a < b) || (++b > c));
        // System.out.println(b);  // 2
        // System.out.println(!(a < b));

        // a += 1;
        // System.out.println(a);
        // a *= 1;
        // System.out.println(a);
        // a -= 1;
        // System.out.println(a);
        // a /= 1;
        // System.out.println(a);
        // a %= 1;
        // System.out.println(a);

        // System.out.println(a < b ? ++a : --a);
        // System.out.println(a);  // 2
    }
}

/**
 * 循环
 */
class MyLoop {
    public static void main(String[] args) {
        // for (int i = 0; i < 10; i++) {
        //     System.out.println(i);
        // }

        // int result = 0;
        // for (int i = 1; i <= 100; i++) {
        //     result += i;
        // }
        // System.out.println(result);

        // for(int i = 2; i < 100; i++) {
        //     boolean flag = true;
        //     for (int j = 2; j <= Math.sqrt(i); j++) {
        //         if (i % j == 0) {
        //             flag = false;
        //             break;
        //         }
        //     }
        //     if (flag){
        //         System.out.println(i);
        //     }
        // }

        // int a = 10;
        // do {
        //     System.out.println("do...While..." + a);
        //     a--;
        // }while (a > 0);

        // int x = 0;
        // while (x >= 0 && x <= 5){
        //     x++;
        // }
        // System.out.println(x);
    }
}

/**
 * 流程分支
 */
class MyBranch {
    public static void main(String[] args) {
        // int month = 7;
        // switch (month) {
        //     case 1:
        //     case 2:
        //     case 3:
        //         System.out.println("第一季度");
        //         break;
        //     case 4:
        //     case 5:
        //     case 6:
        //         System.out.println("第二季度");
        //         break;
        //     case 7:
        //     case 8:
        //     case 9:
        //         System.out.println("第三季度");
        //         break;
        //     case 10:
        //     case 11:
        //     case 12:
        //         System.out.println("第四季度");
        //         break;
        // }

        // Scanner sc = new Scanner(System.in);
        // System.out.println("Enter a string: ");
        // while (sc.hasNext()) {
        //     String str = sc.next();
        //     System.out.println("The input string is " + str);
        //     if (str.equals("q")) {
        //         System.out.println("Welcome !");
        //         break;
        //     }
        // }

        for (int i = 0; i < 5; i++) {
            for (int j = 5 - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

/**
 * 局部变量
 */
class Person {
    String name = "张三";
    int age = 25;

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int age = 26;
        // System.out.println(name);  // 无法从静态上下文中引用非静态 变量
        // System.out.println(age);  // 26

        Person s = new Person();
        s.get_public_vars();
    }

    public void get_public_vars() {
        int age = 27;
        System.out.println(name);  // 张三
        System.out.println(age);  // 27
    }
}

/**
 * 面向对象
 */
class Mobile {
    public String brand;
    public int price;

    public void sendMessage(String name) {
        System.out.println(name + " 你在家吗？之前让我帮你买的" + this.brand + "手机到了。");
    }

    public String getMobileInfo() {
        return "手机品牌: " + this.brand + ", 手机价格: " + this.price;
    }

    public Mobile() {

    }

    public Mobile(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public static void main(String[] args) {
        Mobile m = new Mobile("摩托罗拉", 3299);
        m.sendMessage("张楚兰");
        String info = m.getMobileInfo();

        Mobile m2 = new Mobile();
        String info2 = m2.getMobileInfo();

        System.out.println(info);
        System.out.println(info2);
    }
}

class MyStudent {
    private String name;
    private int age;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public MyStudent(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

class MyListTest {
    public static void main(String[] args) {
        ArrayList<MyStudent> stu = new ArrayList<MyStudent>();
        stu.add(new MyStudent("张三", 25, "男"));
        stu.add(new MyStudent("李四", 26, "男"));
        stu.add(new MyStudent("小芳", 27, "女"));

        System.out.println("old size: " + stu.size());

        stu.remove(2);

        System.out.println("new size: " + stu.size());

        stu.set(0, new MyStudent("王五", 25, "男"));

        for (int i = 0; i < stu.size(); i++) {
            System.out.println(stu.get(i).getName());
            System.out.println(stu.get(i).getAge());
            System.out.println(stu.get(i).getGender());
        }

        System.out.println("====================");

        for (MyStudent s :
                stu) {
            System.out.println(s.getName());
            System.out.println(s.getAge());
            System.out.println(s.getGender());
        }

        System.out.println("====================");

        Iterator<MyStudent> iter = stu.iterator();
        while (iter.hasNext()) {
            MyStudent s = iter.next();
            System.out.println(s.getName());
            System.out.println(s.getAge());
            System.out.println(s.getGender());
        }
    }
}

class MyMapTest {
    public static void main(String[] args) {

        HashMap<String, MyStudent> stu_a_map = new HashMap<String, MyStudent>();
        MyStudent stu_a = new MyStudent("张三", 25, "男");
        stu_a_map.put("studentA", stu_a);

        HashMap<String, MyStudent> stu_b_map = new HashMap<String, MyStudent>();
        MyStudent stu_b = new MyStudent("李四", 26, "男");
        stu_b_map.put("studentA", stu_b);

        HashMap<String, MyStudent> stu_c_map = new HashMap<String, MyStudent>();
        MyStudent stu_c = new MyStudent("小花", 27, "女");
        stu_c_map.put("studentA", stu_c);

        HashMap<String, MyStudent> stu_d_map = new HashMap<String, MyStudent>();
        MyStudent stu_d = new MyStudent("王五", 28, "男");
        stu_d_map.put("studentA", stu_d);

        HashMap<String, MyStudent> stu_e_map = new HashMap<String, MyStudent>();
        MyStudent stu_e = new MyStudent("小芳", 29, "女");
        stu_e_map.put("studentA", stu_e);

        HashMap<String, HashMap[]> map = new HashMap<String, HashMap[]>();
        HashMap[] map_1 = {stu_a_map, stu_b_map, stu_c_map};
        HashMap[] map_2 = {stu_d_map, stu_e_map};
        map.put("1801", map_1);
        map.put("1802", map_2);

        Set<String> keys = map.keySet();
        for (String key :
                keys) {
            HashMap[] m = map.get(key);
            System.out.println("key: " + key + ", value: " + m);
            for (HashMap h :
                    m){
                Set<String> k = h.keySet();
                for (String kk :
                        k) {
                    MyStudent s = (MyStudent)h.get(kk);
                    System.out.println(s.getName());
                    System.out.println(s.getAge());
                    System.out.println(s.getGender());
                }
            }
        }
    }
}