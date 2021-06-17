package leetcode.dailypractice;

import java.util.Arrays;

/**
 * @author ZhangXinYu
 * @date 2021/6/17
 */
public class No62 {
    /**
     * 62. 不同路径
     * <p>
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     *
     * @param m
     * @param n
     * @return 思路：
     * 路径转移类型DP
     * 对于(0,0)这个点来说，它只能往右走、或者往下走。
     * 那么反过来看，哪个点可以到达(2,2)呢？
     * 只能是它的上方(1,2)这个点
     * 或者是它的左方(2,1)这个点
     * 搞清楚这个关系，动态规划的转移方程就可以很容易写出来了：
     * <p>
     * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * 解决了核心逻辑，再把边界条件处理下就可以了。
     * 递归的边界条件是走到了最右边一列、或者是走到了最下面一行。
     * 动态规划正好是反过来的，因为我们是从上到下一行一行推导的。
     * 所以我们要处理下第一行和第一列，将它们都赋予1即可。
     * <p>
     * 时间复杂度：O(M * N)O(M∗N)
     * 空间复杂度：O(M * N)O(M∗N)
     */
    public static int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    f[i][j] = f[i][j - 1] + f[i - 1][j];
                } else if (i > 0) {
                    f[i][j] = f[i - 1][j];
                } else if (j > 0) {
                    f[i][j] = f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * 优化空间复杂度O(n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(No62.uniquePaths(3, 3));
    }
}
