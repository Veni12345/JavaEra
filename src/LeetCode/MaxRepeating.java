package LeetCode;

/**
 * @author: Veni
 * @date: 2022/11/03 十一月 星期四 10:41
 * @description: 字符串中的重复字符
 */
public class MaxRepeating {
    public static void main(String[] args) {

//        String sequence = "ababc", word = "ab";
//        String sequence = "ababc", word = "bc";
//        String sequence = "ababc", word = "ab";
        String sequence = "cabab", word = "ab";
//        String sequence = "a", word = "a";
//        String sequence="aaabaaaabaaabaaaabaaaabaaaabaaaaba", word = "aaaba";
//        String[] split = sequence.split(word);
        int i = maxRepeating(sequence, word);
        System.out.println(i);
    }

    /*public static int maxRepeating(String sequence, String word) {
        if (word.length() > sequence.length()) {
            return 0;
        }
        if (sequence.length() == 1) {
            if (sequence.equals(word)) {
                return 1;
            }
            return 0;
        }
        String substring = sequence.substring(0, word.length());
        String substring1 = sequence.substring(sequence.length()-word.length(), sequence.length());
        boolean b1=sequence.substring(0,word.length()).equals(word);
        boolean b2=sequence.substring(sequence.length()-word.length(), sequence.length()).equals(word);
        if(!b1&&b2){
            sequence = " " + sequence;
        }
        if(b2&&!b1){
//            sequence = sequence + " ";
        }
        String[] split = sequence.split(word);
        int length = split.length;
        return length - 1;

    }*/
    public static int maxRepeating(String sequence, String word) {
        int count = 0;
        String tmp = word;
        while (sequence.contains(word)) {
            word += tmp;
            count++;
        }
        return count;
    }

}
