/**
 * @Author : Created by lity25
 * @Date : 2022/3/2 10:17
 * @Description :
 */
public class InterfaceTest {
    public static void main(String[] args) {
        Te3 te2=new Te3();
        int a = te2.a;  //在接口里面的变量默认都是public static final 的，它们是公共的,静态的,最终的常量.相当于全局常量，可以直接省略修饰符。
    }

    static interface Te{
        int a=10;  //在接口里面的变量默认都是public static final 的，它们是公共的,静态的,最终的常量.相当于全局常量，可以直接省略修饰符。
        private void method1() {
            System.out.println("method3");
        }
        default void method2(){
            System.out.println("method1");
        }
        //Modifier 'protected' not allowed here
//        protected void method3();
        void method();

    }
    abstract static class Te2 implements Te{
        //抽象类实现接口，可以包含抽象方法
        abstract void me2();
    }

    static class Te3 implements Te{
        @Override
        public void method2() {

        }

        @Override
        public void method() {

        }
    }
}
