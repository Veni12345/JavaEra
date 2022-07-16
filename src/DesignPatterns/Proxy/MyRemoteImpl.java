package DesignPatterns.Proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author: Veni
 * @date: 2022/07/13 七月 星期三 17:24
 * @description: Java RMI测试  远程服务实现
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    //超类UnicastRemoteObject的构造器声明了异常，这里需throws异常，以表明构造器正在调用不安全的代码
    protected MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "VENI WORLD";
    }

    public static void main(String[] args) {
        try {
            //产生远程对象
            MyRemote myRemote = new MyRemoteImpl();
            //绑定到registry，客户将使用此注册的的名称在RMI registry中寻找它
            Naming.rebind("RemoteVeni", myRemote);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
