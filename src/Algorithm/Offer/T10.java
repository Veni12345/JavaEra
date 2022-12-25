package Algorithm.Offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Veni
 * @date: 2022/12/11 十二月 星期日 9:53
 * @description: 面试题10
 * 输入一个整数数组和一个整数k，请问数组中有多少个数字之和等于k的连续子数组
 * 使用哈希表  时间复杂度：O(n)
 */
public class T10 {
    public static void main(String[] args) {
//        int[] nums={1,1,1};
//        int[] nums = {3, 1, 2, 4, 2, 5};
//        int k = 7;
        int[] nums = {3, 1 , 4, 2, 5};
        int k = 4;
//        System.out.println(subarraySum(nums, k));
        System.out.println(twoSum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumToCount = new HashMap<>();
        sumToCount.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            Integer orDefaultK = sumToCount.getOrDefault(sum - k, 0);
            count += orDefaultK;
            Integer orDefaultSum = sumToCount.getOrDefault(sum, 0);
            sumToCount.put(sum, orDefaultSum + 1);
        }
        return count;
    }

    public static int[] twoSum(int[] nums, int target) {

        //哈希表中存放 target-num 差值 & 数组下标
        Map<Integer,Integer> sbbMap=new HashMap<>();
//        int []re=new int[2];
        for (int i=0;i< nums.length;i++){
            if(null!=sbbMap.get(nums[i])){
                return new int[]{i, sbbMap.get(nums[i])};
            }
            sbbMap.put(target-nums[i],i);
        }
        return null;
    }
}
