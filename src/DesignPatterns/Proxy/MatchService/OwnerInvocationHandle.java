package DesignPatterns.Proxy.MatchService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Veni
 * @date: 2022/07/20 七月 星期三 16:10
 * @description: 拥有者handle：当用户查看自己的bean时（不得修改自己的HotNotRating）
 */
public class OwnerInvocationHandle implements InvocationHandler {
    PersonBean personBean;

    public OwnerInvocationHandle(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    //每次proxy的方法被调用，将导致此方法被调用
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            //当方法是get、set时直接调用
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            } //当方法是setHotNotRating时，抛出异常，表示不允许修改自己的评分
            else if (method.getName().equals("setHotNotRating")) {
                throw new IllegalAccessException();
            }
            //当方法是get、set时直接调用
            else if (method.getName().startsWith("set")) {
                return method.invoke(personBean, args);
            }

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
