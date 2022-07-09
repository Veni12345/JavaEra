package Basics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Veni
 * @date: 2022/06/29 六月 星期三 20:49
 * @description: 创建对象的 5种方式
 */
public class CreateObject {
    public static void main(String[] args) {
        //1.使用new关键字
        Dog dog1=new Dog("Cici",2);

        //2.使用反射的Class类的newInstance()方法
        try {
            Dog dog2=Dog.class.newInstance();
            dog2.setName("Cici2");
            dog2.setAge(3);
            System.out.println(dog2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //3.使用反射的Constructor的newInstance方法
        try{
            Dog dog3=Dog.class.getConstructor().newInstance();
            dog3.setName("Cici3");
            dog3.setAge(3);
            System.out.println(dog3);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //4.使用对象克隆clone（）方法
        try{
            Dog dog4= (Dog) dog1.clone();
            dog4.setName("Cici4");
            dog4.setAge(4);
            System.out.println(dog4);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //5.使用反序列化 ObjectInputStream的readObject()方法
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\Asterion\\Desktop\\Veni\\JavaEra\\src\\Basics\\Dog.txt"));
            Dog dog5= (Dog) ois.readObject();
            dog5.setName("Cici5");
            dog5.setAge(5);
            System.out.println(dog5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
