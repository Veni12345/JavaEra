package Basics;

/**
 * @author: Veni
 * @date: 2022/09/11 九月 星期日 8:37
 * @description:
 */
public class Demo2 {
    public static void main(String[] args) {
        /* 测试final关键字 */
        String s1="veni";
        String s2="venv";
        String s5="vedi";
        String s4="venivedi";
        String s6=s1+s5;
        String s3=new String("veni");
        boolean e1 = s1==(s2);
        boolean e2 = s1==(s3);  //false
        boolean e3 = s4==(s6);   //false

        final String sf1="veni_F";
        final String sf2="venv_F";
        final String sf3=new String("veni_F");
        final String sf5="vedi_F";
        final String sf4="veni_Fvedi_F";
        final String sf6=sf1+sf5;
        boolean ef1 = sf1==(sf2);
        boolean ef2 = sf1==(sf3);  //false final可以修饰堆中变量
        boolean ef3 = sf4==(sf6);    //true


        System.out.println("===================================");

    }
}
