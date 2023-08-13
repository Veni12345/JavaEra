package Collection;

import java.util.HashMap;

/**
 * @author: Veni
 * @date: 2023/08/11 八月 星期五 13:45
 * @description:
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>(3);
        HashMap<Integer, Integer> hashMap1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> hashMap2 = new HashMap<Integer, Integer>(3);
        HashMap<Integer, Integer> hashMap3 = new HashMap<Integer, Integer>(7);
        HashMap<Integer, Integer> hashMap4 = new HashMap<Integer, Integer>(13);
        hashMap.put(2, 2);
        hashMap1.put(4, 5);
        hashMap2.put(3, 3);
        hashMap3.put(10, 10);
        hashMap.put(18, 18);
        hashMap.put(26, 26);
//        hashMap.put(18,18);
        System.out.println(hashMap);

        //while循环
        for (int binCount = 0; ; ++binCount) {
            if (binCount == 10){
                System.out.println(binCount);
                break;
            }
        }

//        tag:
//        for (int binCount = 0; ; ++binCount) {
//            if (binCount == 10){
//                System.out.println(binCount);
//                break tag;
//            }
//        }

        System.out.println("hhh");

    }
}
