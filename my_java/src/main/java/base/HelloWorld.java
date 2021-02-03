package base;

import javax.sql.rowset.spi.SyncResolver;
import java.util.Scanner;

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
class MyLoop{
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

class MyBranch{
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
