package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/24
 */
public class No149 {
    /**
     * 149. 直线上最多的点数
     * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
     * <p>
     * 示例 1：
     * 输入：points = [[1,1],[2,2],[3,3]]
     * 输出：3
     * <p>
     * 示例 2：
     * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * 输出：4
     * <p>
     * 提示：
     * 1 <= points.length <= 300
     * points[i].length == 2
     * -104 <= xi, yi <= 104
     * points 中的所有点 互不相同
     */

    /**
     * 思路：
     * 斜率公式k=(y1-y2)/(x1-x2)
     * 开始拿两个点确定一条直线然后用第三个点和前两个里随意一个点求出斜率对比之前的两个点的斜率，相等则表示在一条直线上
     *
     * @param points
     * @return
     */
    public static int maxPoints(int[][] points) {
        int len = points.length;
        int ans = 1;
        for (int i = 0; i < len; i++) {
            int[] x = points[i];
            for (int j = i + 1; j < len; j++) {
                int[] y = points[j];
                int res = 2;
                for (int k = j + 1; k < len; k++) {
                    int[] z = points[k];
                    int k1 = (y[1] - x[1]) * (z[0] - y[0]);
                    int k2 = (z[1] - y[1]) * (y[0] - x[0]);
                    if (k1 == k2) {
                        res++;
                    }
                }
                ans = Integer.max(ans, res);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points1 = new int[][]{{1, 1}, {2, 2}, {3, 3}};//3
        int[][] points2 = new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 4}, {1, 4}};//4
        System.out.println(No149.maxPoints(points2));
    }
}
