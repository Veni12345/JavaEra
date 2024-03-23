package Algorithm.Offer;

import java.util.Stack;

/**
 * @author: Veni
 * @date: 2024/03/23 三月 星期六 20:09
 * @description: 小行星相撞 T37
 * 栈结构
 */
public class AsteroidCollision {
    public static void main(String[] args) {
        int[] asteroids = new int[]{4, 5, -6, 4, 8, -5};
        System.out.println(asteroidsCollision(asteroids));
    }

    public static int[] asteroidsCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int as : asteroids) {
            while (!stack.isEmpty() && -as > stack.peek() && stack.peek() > 0) {
                stack.pop();
            }

            if (stack.isEmpty() || as > 0 || stack.peek() < 0) { //入栈操作
                stack.push(as);
            } else if (!stack.isEmpty() && as < 0 && stack.peek() == -as) { //相等时弹出最顶的
                stack.pop();
            }
        }

        //转化为int数组
        return stack.stream().mapToInt(i -> i).toArray();
    }
}
