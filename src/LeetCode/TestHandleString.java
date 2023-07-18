package LeetCode;

/**
 * @author: Veni
 * @date: 2022/11/08 十一月 星期二 11:11
 * @description:
 */
public class TestHandleString {
    public static void main(String[] args) {
        String al = "ac";
        String[] wo = new String[]{"acc", "acd", "bsd"};
        int i = countConsistentStrings(al, wo);
        System.out.println("=========1684. 统计一致字符串的数目=========");
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        int mask = 0;
        for (int i = 0; i < allowed.length(); i++) {
            char c = allowed.charAt(i);
            int i1 = c - 'a';
            int i2 = 1 << i1;
            mask |= i2;
        }
        int res = 0;
        for (String word : words) {
            int mask1 = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                mask1 |= 1 << (c - 'a');
            }
            if ((mask1 | mask) == mask) {
                res++;
            }
        }
        return res;
    }
}
