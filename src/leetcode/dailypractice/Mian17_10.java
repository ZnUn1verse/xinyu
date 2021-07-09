package leetcode.dailypractice;

import java.util.*;

/**
 * @author ZhangXinYu
 * @date 2021/7/9
 */
public class Mian17_10 {
    /**
     * 面试题 17.10. 主要元素
     * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
     * <p>
     * 示例 1：
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     * <p>
     * 示例 2：
     * 输入：[3,2]
     * 输出：-1
     * <p>
     * 示例 3：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     */
    /**
     * 哈希表
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > len / 2) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Mian17_10 main = new Mian17_10();
        System.out.println(main.majorityElement2(new int[]{6, 5, 5}));
    }

    /**
     * 摩尔投票
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int len = nums.length, major = nums[0], cnt = 1;
        for (int i = 1; i < len; i++) {
            if (cnt == 0) {
                cnt = 1;
                major = nums[i];
                continue;
            }
            cnt = (major == nums[i] ? cnt + 1 : cnt - 1);
        }
        cnt = 0;
        for (int num : nums) {
            if (num == major) {
                cnt++;
            }
        }
        return cnt > len / 2 ? major : -1;
    }
}
