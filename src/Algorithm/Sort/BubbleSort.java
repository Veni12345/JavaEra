package Algorithm.Sort;

/**
 * @author: Veni
 * @date: 2022/09/20 九月 星期二 14:02
 * @description:
 */
public class BubbleSort {

    public static void bubble1(int[] arr, int length) {
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble2(int[] arr, int length) {
        boolean flag = false;
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //发生交换，flag=true
                    flag = true;
                }
            }
            // 若没发生交换，则说明数列已有序。
            if (!flag) {
                break;
            }
        }
    }

    /* test */
    public static void main(String[] args) {
        int array[] = {5, 3, 6, 4, 2};
//        bubble1(array, array.length);
        bubble2(array, array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array);
    }
}
