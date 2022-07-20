package DesignPatterns.Proxy.MatchService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Veni
 * @date: 2022/07/20 七月 星期三 16:20
 * @description: 非拥有者handle：当用户查看自别人的bean时（不得set）
 */
public class NonOwnerInvocationHandle implements InvocationHandler {
    PersonBean personBean;

    public NonOwnerInvocationHandle(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    //每次proxy的方法被调用，将导致此方法被调用
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            //当方法是get、setHotNotRating 时直接调用
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("setHotNotRating")) {
                return method.invoke(personBean, args);
            }
            //当方法是set时，抛出异常，表示不允许修改别人的相关信息
            else if (method.getName().startsWith("set")) {
                throw new IllegalAccessException();
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
