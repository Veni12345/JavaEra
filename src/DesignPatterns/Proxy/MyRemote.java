package DesignPatterns.Proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author: Veni
 * @date: 2022/07/13 七月 星期三 17:21
 * @description: Java RMI测试   远程接口
 */
public interface MyRemote extends Remote {

    /**
     *
     * @return
     * @throws RemoteException 所有的方法都必须声明RemoteException
     */
    public String sayHello() throws RemoteException;
}
