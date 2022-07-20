package DesignPatterns.Proxy.MatchService;

import java.lang.reflect.Proxy;

/**
 * @author: Veni
 * @date: 2022/07/20 七月 星期三 16:25
 * @description: 测试配对服务——保护代理
 *  配对服务，Hot代表喜欢，Not表示不喜欢
 */
public class MatchMarkingTestDrive {
    public static void main(String[] args) {
        MatchMarkingTestDrive match=new MatchMarkingTestDrive();
        match.drive();
    }

    //构造函数，初始化工作
    public MatchMarkingTestDrive() {
    }

    //创建一个代理，将它的方法调用转发给OwnerInvocationHandle
    PersonBean getOwnerProxy(PersonBean personBean) {
        //利用Proxy的静态newProxyInstance方法创建代理
        PersonBean person = (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(),  //只能是接口，不能是类
                new OwnerInvocationHandle(personBean));
        return person;
    }

    //创建一个代理，将它的方法调用转发给NonOwnerInvocationHandle
    PersonBean getNonOwnerProxy(PersonBean personBean) {
        //利用Proxy的静态newProxyInstance方法创建代理
        PersonBean person = (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(),
                new NonOwnerInvocationHandle(personBean));
        return person;
    }


    public void drive() {
        //实例化简单人员对象
        PersonBean veni = new PersonBeanImpl();
        veni.setName("Veni");
        veni.setGender("M");
        veni.setInterests("code");

        /** 创建拥有者代理 **/
        PersonBean ownerProxy = this.getOwnerProxy(veni);
        ownerProxy.setInterests("money");
        System.out.println("Name is " + ownerProxy.getName() + "; Interests are " + ownerProxy.getInterests());
        try {
            ownerProxy.setHotNotRating(100);
        } catch (Exception e) {
//            throw new IllegalAccessException();
            System.out.println("Can't set rating from own proxy");
        }
        System.out.println("own rating " + ownerProxy.getHotNotRating());
        System.out.println();


        /** 创建非拥有者代理 **/
        PersonBean nonOwnerProxy = this.getNonOwnerProxy(veni);
        try {
            nonOwnerProxy.setInterests("bugs");
        } catch (Exception e) {
            System.out.println("Can't set interests from nonowner proxy");
        }
        nonOwnerProxy.setHotNotRating(8);
        System.out.println("Rating is " + nonOwnerProxy.getHotNotRating());

    }
}
