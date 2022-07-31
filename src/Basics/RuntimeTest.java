package Basics;

/**
 * @author: Veni
 * @date: 2022/07/29 七月 星期五 15:50
 * @description:
 */
public class RuntimeTest {
    public static void main(String[] args) {
        /**
         * Runtime测试
         */
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime);
        System.out.println(Runtime.version());
        System.out.println(runtime.availableProcessors()); //获取本机CPU内核数 （并发访问最佳）
        System.out.println("[1]MAX_MEMORY:" + runtime.maxMemory());
        System.out.println("[1]TOTAL_MEMORY:" + runtime.totalMemory());  //Returns the total amount of memory in the Java virtual machine.
        System.out.println("[1]FREE_MEMORY:" + runtime.freeMemory());  //Returns the amount of free memory in the Java Virtual Machine.
        runtime.gc();

    }
}
