package DesignPatterns.Singleton;

/**
 * 单例模式
 */
public class Singleton {
    //私有构造方法
    private Singleton() {
    }

    //私有静态实例
    private volatile static Singleton uniqueInstance;

    //公共静态获取实例方法
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    return new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
