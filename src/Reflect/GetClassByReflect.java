package Reflect;

/**
 * @author: Veni
 * @date: 2022/07/12 七月 星期二 22:13
 * @description: 利用反射获取对象的三种方式
 */
public class GetClassByReflect {
    public static void main(String[] args) {
//        1 Object ——> getClass();
        Cat cat1 = new Cat();//这一new 产生一个Student对象，一个Class对象。
        Class catClass = cat1.getClass();//获取Class对象
        System.out.println(catClass.getName());

//        2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
        Cat cat2 = new Cat();//这一new 产生一个Student对象，一个Class对象。
        Cat.class.getName();
        System.out.println(Cat.class.getName());

//        3 通过Class类的静态方法：forName（String className）(常用)
        String className = "Reflect.Cat";
        try {
            Class catClass3 = Class.forName(className);
            System.out.println(catClass3);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
