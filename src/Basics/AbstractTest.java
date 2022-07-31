package Basics;

/**
 * @Author : Created by lity25
 * @Date : 2022/3/2 10:11
 * @Description : 测试 abstract 关键字
 */
public class AbstractTest {
    public static void main(String[] args) {
        SonClass3 sonClass3 = new SonClass3();
        sonClass3.me1();
        sonClass3.me2();
    }

    abstract static class abstractSuperClass1 {
        abstract void me1();

        void me2() {
            //抽象类中可以包含非抽象方法，但要实现方法；
            System.out.println("abstractSuperClass1.me2()");
        }
        //Illegal combination of modifiers: 'final' and 'abstract'
//        abstract final void me3();

        //静态方法：抽象类中可以包含静态方法；接口中也可以包含静态方法
        static void staMe() {
            System.out.println("抽象类中可以包含静态方法");
        }
    }

    abstract static class abstractSonClass2 extends abstractSuperClass1 {
        //override
        abstract void me1();

        //override
        void me2() {
            //重写
            System.out.println("abstractSuperClass2.me2()");
        }
    }

    static class SonClass3 extends abstractSuperClass1 {
        @Override
        void me1() {
            System.out.println("SonClass3.me1()");
        }
    }
}
