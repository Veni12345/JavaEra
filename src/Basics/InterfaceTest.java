package Basics;

/**
 * @Author : Created by lity25
 * @Date : 2022/3/2 10:17
 * @Description :
 */
public class InterfaceTest {
    public static void main(String[] args) {
        Te3 te2 = new Te3();
        int a = te2.a;  //在接口里面的变量默认都是public static final 的，它们是公共的,静态的,最终的常量.相当于全局常量，可以直接省略修饰符。
    }

    static interface Te {
        int a = 10;  //在接口里面的变量默认都是public static final 的，它们是公共的,静态的,最终的常量.相当于全局常量，可以直接省略修饰符。

        private void method1() {
            System.out.println("method3");
        }

        default void defaultMethod2() {  //JDK1.8新特性：接口中使用default方法，实现类可以不用实现此方法
            System.out.println("method1");
        }

        //Modifier 'protected' not allowed here
//        protected void method3();


        static void method() {   //接口中可以包含静态方法
            System.out.println("接口中可以包含静态方法");
        }

//        void normalMethod();  //普通方法

    }


    abstract static class Te2 implements Te {
        //抽象类实现接口，可以包含抽象方法
        abstract void me2();
    }


    static class Te3 implements Te {
    }
}
