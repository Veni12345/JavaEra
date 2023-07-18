package Algorithm.Offer;

/**
 * @author: Veni
 * @date: 2022/12/25 十二月 星期日 16:40
 * @description: 领扣：第3题: 不包含重复字符的最长子字符串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * hash表、双指针
 */
public class T16 {
    public static void main(String[] args) {
        String s = "abcabcbb";
        String s1 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstringEx(s));
        System.out.println(lengthOfLongestSubstringEx(s1));
    }

    /**
     * 哈希表、双指针
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int j = -1, max = 0;
        int count[] = new int[256];   //ASCII码表表示的字符包含256个
        for (int i = 0; i < s.length(); i++) {
            count[(int) s.charAt(i)]++;
            if (hasDoubleChar(count)) {
                ++j;
                count[s.charAt(j)]--;
            }
            max = Math.max(i - j, max);
        }
        return max;
    }

    /**
     * 判断字符串是否包含两个以上字符
     *
     * @param count
     * @return 包含重复字符则返回true
     */
    private static boolean hasDoubleChar(int count[]) {
        for (int c : count) {
            if (c > 1)
                return true;
        }
        return false;
    }


    /**
     * 由于上方法每次扫描一遍哈希表，浪费了时间
     * 优化
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringEx(String s) {
        int j = -1, max = 0;
        int count[] = new int[256];   //ASCII码表表示的字符包含256个
        int countDup = 0;  //记录哈希表中大于1的数字的个数
        for (int i = 0; i < s.length(); i++) {
            count[(int) s.charAt(i)]++;
            if (count[s.charAt(i)] == 2) {
                countDup++;
            }
            while (countDup > 0) {
                ++j;
                count[s.charAt(j)]--;
                if (count[s.charAt(j)] == 1) {
                    countDup--;
                }
            }
            max = Math.max(i - j, max);
        }
        return max;
    }


}
