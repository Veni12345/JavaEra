package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: Veni
 * @date: 2022/07/05 七月 星期二 21:52
 * @description:
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cla = Cat.class;
        //获取包
        Package aPackage = cla.getPackage();
        //获得父类
        Class<?> superclass = cla.getSuperclass();
        //获取父接口
        Class<?>[] interfaces = cla.getInterfaces();

        //类结构处理
        //获取所有构造方法
        Constructor<?>[] declaredConstructor = cla.getDeclaredConstructors();
        Constructor<?>[] constructors = cla.getConstructors();
        //获取指定构造方法
        Constructor<?> constructor = cla.getConstructor(String.class, Integer.class);
        Object kiki = constructor.newInstance("Kiki", 1);  //通过构造方法实例化对象


        //获取本类中的全部方法
        Method[] declaredMethods = cla.getDeclaredMethods();
        //获取包含父类中的全部方法
        Method[] methods = cla.getMethods();
        //获取类中的指定方法
//        Method declaredMethod = cla.getDeclaredMethod(null);
//        cla.getMethod()

        //获取类中的属性
        Field[] declaredFields = cla.getDeclaredFields(); //获取Class对象所表示的类或接口的所有(包含private修饰的)字段,不包括继承的字段
        Field[] fields = cla.getFields(); //获取修饰符为public的字段，包含继承字段
        Field catName = cla.getDeclaredField("catName"); //获取指定name名称的(包含private修饰的)字段，不包括继承的字段
//        cla.getField(" "); //	获取修饰符为public的字段，包含继承字段
        //getType
        String name = catName.getType().getName();
        String typeName = catName.getType().getSimpleName();


        System.out.println(cla);


        //invoke
        Class<?> aClass = Class.forName("Reflect.Cat");
        String value = "wiwi";  //属性内容
        //调用无参构造实例化
        //1.任何情况下想要保存类中的属性或者调用类中的方法都必须保证有实例化对象，既然不允许导入包，那么就反射实例化
        Object object = aClass.getDeclaredConstructor().newInstance();
        //2.如果进行方法的调用，就要获取方法的名称
        String setMethodName = "setCatName";
        Method setMethod = aClass.getDeclaredMethod(setMethodName, String.class);
        setMethod.invoke(object, value); //等价于Cat.setCatName(value)

        String getCatName = "getCatName";
        Method getMethod = aClass.getDeclaredMethod(getCatName);
        System.out.println(getMethod.invoke(object));


    }
}
