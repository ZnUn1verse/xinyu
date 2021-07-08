package leetcode.dailypractice;

import java.util.*;

/**
 * @author ZhangXinYu
 * @date 2021/7/8
 */
public class No930 {
    /**
     * 930. 和相同的二元子数组
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * 子数组 是数组的一段连续部分。
     * <p>
     * 示例 1：
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 如下面黑体所示，有 4 个满足题目要求的子数组：
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * <p>
     * 示例 2：
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     * <p>
     * <p>
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * nums[i] 不是 0 就是 1
     * 0 <= goal <= nums.length
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        //细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        //例如输入[1,1,0]，goal = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        //输入：[3,1,1,0] goal = 2时则不会漏掉
        //因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
        map.put(0, 1);
        int count = 0;
        int presum = 0;
        for (int x : nums) {
            presum += x;
            //当前前缀和已知，判断是否含有 presum - goal的前缀和，那么我们就知道某一区间的和为 goal 了。
            if (map.containsKey(presum - goal)) {
                count += map.get(presum - goal);//获取次数
            }
            //更新
            map.put(presum, map.getOrDefault(presum, 0) + 1);
        }
        return count;
    }
}
