package Collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: Veni
 * @date: 2023/10/09 十月 星期一 13:41
 * @description:
 */
public class PriorityQueueTest {
    /* 1.使用PriorityQueue初始化一个最大堆 */
    // 创建一个自定义的Comparator对象，用于指定比较规则
    /*Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer num1, Integer num2) {
            // 按照降序排列，即大的元素优先级高
            return num2 - num1;
        }
    };*/
    Comparator<Integer> maxHeapComparator = (num1, num2) -> {
        // 按照降序排列，即大的元素优先级高
        return num2 - num1;
    };

    // 使用自定义的Comparator创建PriorityQueue，即最大堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(maxHeapComparator);

    /* 2.自实现，使用数组 */
    public static class MaxHeap {
        private int[] heap;
        private int size;

        public MaxHeap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        public void insert(int value) {
            if (size == heap.length) {
                throw new IllegalStateException("Heap is full");
            }

            // 将新元素放入堆的末尾
            heap[size] = value;

            // 调整堆的结构，使其满足最大堆的性质
            siftUp(size);

            size++;
        }

        private void siftUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (heap[index] <= heap[parentIndex]) {
                    break;  // 已经满足最大堆的性质，退出循环
                }
                swap(index, parentIndex);
                index = parentIndex;
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        public void printHeap() {
            System.out.println(Arrays.toString(heap));
        }


    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(10);

        maxHeap.printHeap();
    }
}
