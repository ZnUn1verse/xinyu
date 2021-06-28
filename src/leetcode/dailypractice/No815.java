package leetcode.dailypractice;

import java.util.*;

/**
 * @author ZhangXinYu
 * @date 2021/6/28
 */
public class No815 {
    /**
     * 815. 公交路线
     * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
     * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
     * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
     * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
     * <p>
     * 示例 1：
     * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
     * 输出：2
     * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
     * <p>
     * 示例 2：
     * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
     * 输出：-1
     * <p>
     * <p>
     * 提示：
     * 1 <= routes.length <= 500.
     * 1 <= routes[i].length <= 105
     * routes[i] 中的所有值 互不相同
     * sum(routes[i].length) <= 105
     * 0 <= routes[i][j] < 106
     * 0 <= source, target < 106
     */
    /**
     * 思路：bfs
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        //<station,{bus}>-每个站都被哪些公交车经过
        HashMap<Integer, List<Integer>> s2b = new HashMap<>();
        for (int b = 0; b < routes.length; b++) {
            for (int s : routes[b]) {
                int t1 = b;
                if (!s2b.containsKey(s)) {
                    s2b.put(s, new ArrayList() {{
                        add(t1);
                    }});
                } else s2b.get(s).add(b);
            }
        }
        //记录已经坐了哪些公交车
        int[] memory2b = new int[routes.length];  //知道多长
        //bfs-收集当前station辐射到的station
        Queue<Integer> q = new LinkedList<>();
        //先压入起始车站
        q.offer(source);
        //坐过多少公交车
        int count = 0;

        //bfs
        while (!q.isEmpty()) {
            int size = q.size();
            count++;
            //每次q收集cur辐射到的所有station，都是cur可以不用换乘到达的车站
            //while(size--)结束，没有找到target说明需要换乘一次，count++
            while (size-- > 0) {
                int cur = q.poll();
                //经过cur的所有车
                for (int car : s2b.get(cur)) {
                    if (memory2b[car] == 1) continue;
                    memory2b[car] = 1;  //标记已经访问过的car
                    for (int s : routes[car]) {
                        if (s == target) return count;
                        if (s == cur) continue;
                        q.offer(s);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        No815.numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6);
    }
}
