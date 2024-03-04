package Algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Veni
 * @date: 2024/03/03 三月 星期日 17:07
 * @description:
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        int[][] grid1 = {{1, 1, 0, 0}, {1, 0, 0, 1}, {1, 1, 0, 1}, {0, 0, 1, 0}};
        int maxAreaOfIsland = maxAreaOfIsland(grid1);
        System.out.println(maxAreaOfIsland);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        //最大区域
        int maxArea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int area = getArea(grid, visited, i, j);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    /**
     * 广度优先遍历
     *
     * @param grid
     * @param visited
     * @param i
     * @param j
     * @return
     */
    private static int getArea(int[][] grid, boolean[][] visited, int i, int j) {
        //使用队列保存矩阵坐标
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        //定义矩阵变化上下左右
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int area = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.remove();
            area++;

            for (int[] dir : dirs) {
                int r = pos[0] + dir[0];
                int c = pos[1] + dir[1];
                if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length
                        && grid[r][c] == 1
                        && !visited[r][c]) {
                    queue.add(new int[]{r, c});
                    visited[r][c] = true;
                }
            }
        }
        return area;
    }
}
