/**
 * 8.虚拟机字节码执行调用
 * 8.4 方法调用--动态分派--重写方法
 */
public class JVM_DD_Test {
    public static void main(String[] args) {
        JVM_DD_Human man = new JVM_DD_Man();
        JVM_DD_Human woman = new JVM_DD_Woman();
        JVM_DD_Human staticDispatch = new JVM_DD_Human();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(new JVM_DD_Man());
        staticDispatch.sayHello(woman);
    }
}
