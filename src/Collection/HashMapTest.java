package Collection;

import java.util.HashMap;


/**
 * @author: Veni
 * @date: 2023/08/11 八月 星期五 13:45
 * @description:
 */
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;


    public static void main(String[] args) {
        int i = tableSizeFor(3);
        int i1 = tableSizeFor(9);
        int i2 = tableSizeFor(11);
        int i4 = tableSizeFor(30);

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>(3);
        hashMap.put(2, 2);
        hashMap.put(18, 18);
        hashMap.put(26, 26);


        HashMap<Integer, Integer> hashMap1 = new HashMap<Integer, Integer>();
        hashMap1.put(4, 5);
        hashMap1.put(8, 9);

        HashMap<Integer, Integer> hashMap2 = new HashMap<Integer, Integer>(3);
        HashMap<Integer, Integer> hashMap3 = new HashMap<Integer, Integer>(7);
        HashMap<Integer, Integer> hashMap4 = new HashMap<Integer, Integer>(13);
        hashMap2.put(3, 3);
        hashMap3.put(10, 10);

//        hashMap.put(18,18);
        System.out.println(hashMap);

        hashMap.get(2);

        //while循环
        for (int binCount = 0; ; ++binCount) {
            if (binCount == 10) {
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


    /**
     * 1. cap 是指定的哈希表容量。
     * 2. Integer.numberOfLeadingZeros(cap - 1) 返回 cap - 1 的二进制表示中前导零的数量。
     * 这个值告诉我们 cap - 1 之前有多少位是零，而 -1 会将所有的位都变为 1。
     * 3. int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1) 会将 -1 向右移动 Integer.numberOfLeadingZeros(cap - 1) 位，相当于将其变为一个以 1 开头的二进制数字。
     * 这个操作实际上会把前导零转换为后导零，并将其他位设置为 1。
     * 4. (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1 返回一个合适的哈希表大小。
     * 如果 n 小于 0，说明 cap 为 0 或负数，因此返回 1；如果 n 大于等于 MAXIMUM_CAPACITY，则返回 MAXIMUM_CAPACITY；否则，返回 n + 1。
     * @param cap
     * @return
     */
    static int tableSizeFor(int cap) {
        int n = -1 >>> numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Count leading 0's
        if (i <= 0)
            return i == 0 ? 32 : 0;
        int n = 31;
        if (i >= 1 << 16) { n -= 16; i >>>= 16; }
        if (i >= 1 <<  8) { n -=  8; i >>>=  8; }
        if (i >= 1 <<  4) { n -=  4; i >>>=  4; }
        if (i >= 1 <<  2) { n -=  2; i >>>=  2; }
        return n - (i >>> 1);
    }
}
