package DesignPatterns.Proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author: Veni
 * @date: 2022/07/13 七月 星期三 17:32
 * @description: Java RMI测试  客户端
 */
public class MyRemoteClient {
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    public void go() {
        try {
//            MyRemote service = (MyRemote) Naming.lookup(rmi://127.0.0.1/RemoteVeni);
            MyRemote service = (MyRemote) Naming.lookup("127.0.0.1/RemoteVeni");
            String s=service.sayHello();
            System.out.println(s);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
