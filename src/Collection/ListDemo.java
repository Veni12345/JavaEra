package Collection;

import java.util.*;

/**
 * @Author : Created by lity25
 * @Date : 2022/3/18 11:24
 * @Description :
 */
public class ListDemo {
    public static void main(String[] args) {
        String ss = "60000000.60200000.60200002";
        String ss2 = "60000000.60300000";
        List<String> list = new ArrayList<>(2);
        List<String> list2 = new ArrayList<>(15);
        list.add(ss);
        list.add(ss2);
        list.add(ss2);

        int oldCapacity = list.size();
        int i = oldCapacity >> 1;
        int i1 = 55 >> 1;
        double v = 55 * 1.5;
        int i2 = 66 >> 1;
        double i21 = 66 * 1.5;
        int i3 = 72 >> 1;
        double i31 = 72 * 1.5;

        int i4 = list.indexOf(null);

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        List<String> list3 = new ArrayList<>(list);

        LinkedList<String> llist1 = new LinkedList<>();
        Stack<String> stack = new Stack<String>();
        //当需要使用队列时也就首选ArrayDeque了(次选是LinkedList)。
        Queue<String> strings = new ArrayDeque<>();
        Queue<String> stacka = new LinkedList<>();

        llist1.add(ss);
        llist1.add(ss2);



    }
}
