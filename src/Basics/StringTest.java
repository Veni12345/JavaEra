package Basics;

/**
 * @author: Veni
 * @date: 2022/07/29 七月 星期五 11:11
 * @description:
 */
public class StringTest {
    public static void main(String[] args) {
        String a="1.";
        String b="1.2.";
        String c="1.2.";
        String d="1.2.3";

        boolean b1 = b.startsWith(a);
        boolean b2 = b.startsWith(c);
        boolean b3 = b.startsWith(d);
        System.out.println(b1+" "+b2+" "+b3);


        String fi="MA_034";
        String[] s = fi.split("_");
        System.out.println(s);
    }



}
