/**
 * 8.虚拟机字节码执行调用
 * 8.3 方法调用--静态分派--重载方法
 * 虚拟机（或者说编译器）在重载时是通过参数的静态类型而不是实际类型作为判定依据。
 * 由于静态类型在编译期可知，于是编译阶段已经决定了会使用哪个重载版本
 */
public class JVM_StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello! guy");
    }

    public void sayHello(Man guy) {
        System.out.println("hello! gentleman");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello! lady");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        JVM_StaticDispatch staticDispatch = new JVM_StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
