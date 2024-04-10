package Collection;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println("hhhhhh"+i);
            int i1 = i ^ 1;
            System.out.println(i1);
        }


        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        int as = 15 >> 1; //7
        int as1 = 15 / 2; //7

        System.out.println(as + " " + as1);

        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);

        Vector<Integer> vector = new Vector<>();


        //栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);

        LinkedList<Integer> list = new LinkedList<>();
        list.push(2);

        //队列
        Queue<Integer> queue = new LinkedList();
        queue.offer(1);
        queue.peek();
        queue.poll();

        //双端队列
        Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(12);


    }
}
