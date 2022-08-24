package Basics;

import com.sun.jdi.IntegerType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Created by lity25
 * @Date : 2022/1/19 10:02
 * @Description : 基本类型 测试类
 */
public class Demo<T> {
    public static void main(String[] args) {
        /** hashCode */
        Object o = new Object();
        System.out.println(o);
        System.out.println(o.hashCode());
        System.out.println(Integer.toHexString(o.hashCode()));  //转16进制
        System.out.println(new Dog().hashCode());
        System.out.println(System.identityHashCode(o));   //不过和 Object.hashcode 不同的是，该方法会无视重写的hashcode
        /** hashCode生成方法 */
        /*  通过当前状态值进行异或（XOR）运算
        else {
            // Marsaglia's xor-shift scheme with thread-specific state
            // This is probably the best overall implementation -- we'll
            // likely make this the default in future releases.
            unsigned t = Self->_hashStateX ;
            t ^= (t << 11) ;
            Self->_hashStateX = Self->_hashStateY ;
            Self->_hashStateY = Self->_hashStateZ ;
            Self->_hashStateZ = Self->_hashStateW ;
            unsigned v = Self->_hashStateW ;
            v = (v ^ (v >> 19)) ^ (t ^ (t >> 8)) ;
            Self->_hashStateW = v ;
            value = v ;
        } */


        /**
         * boolean中if赋值
         */
        Boolean flag = false;
        if (flag = true) {
            System.out.println(flag);
        }

        /**
         * 基本类型包装初始化值
         */
        Boolean boo = null;
        Byte by = null;
        Short sh = null;
        Integer in = null;
        Integer integer = new Integer(0);
        Long lo = null;
        Character ch = null;
        Float fo = null;
        Double dou = null;
        System.out.println(boo + " " + by + " " + sh + " " + integer + " " + lo + " " + ch + " " + fo + " " + dou);

        /**
         * 基本类型级限制测试
         */
        int a1 = Integer.MIN_VALUE, a2 = Integer.MAX_VALUE, a3 = (short) a2, a5 = 2100000000;
        short a6 = (short) a5; //向下转型，精度丢失
        long a4 = a2;  //
        String a5String = String.valueOf(a5);
//        short a62=Short.valueOf(a5String);

        long l1 = Long.MIN_VALUE, l2 = Long.MAX_VALUE;
        System.out.println(a1 + " " + a2);
        double d1 = Double.MIN_VALUE, d2 = Double.MAX_VALUE, d3 = (float) d2;
        System.out.println(d1 + " " + d2);

        limitMethod(a1, a2);
        byte b1 = Byte.MIN_VALUE, b2 = Byte.MAX_VALUE;
        limitMethod(b1, b2);
        float f = (float) 3.0;
        Object kk = 'f';
        String sd = "fsdfsd\0";
        char cc = '\u0634';
        System.out.println(sd);

        String aaa = "fsadfjsa";
        String bbb = new String("fsadfjsa");
        Integer cc0 = 12;
        Integer cc1 = 12;
        Integer cc2 = new Integer(12);
        System.out.println(aaa == bbb + " ");
        System.out.println(cc0 == cc1);
        System.out.println(cc0 == cc2);

    }

    public static <T extends Number> void limitMethod(T t1, T t2) {
        Class<?> aClass = t1.getClass();
        System.out.println(aClass);
        System.out.println(t1 + " " + t2);
        Double i1 = t1.doubleValue() - 1;
        Double i2 = t2.doubleValue() + 1;
        if (aClass.toString().equals("class java.lang.Integer")) {
            System.out.println(i1.intValue() + " " + i2.intValue());
        } else {
            System.out.println(i1.toString() + " " + i2.toString());
        }
    }
}
