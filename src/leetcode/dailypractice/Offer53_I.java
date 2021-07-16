package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/7/16
 */
public class Offer53_I {
    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * 统计一个数字在排序数组中出现的次数。
     * <p>
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     * <p>
     * <p>
     * 限制：
     * 0 <= 数组长度 <= 50000
     * <p>
     * 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int count = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        while (l < nums.length && nums[l++] == target) {
            count++;
        }
        return count;
    }

    /**
     * 可以
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int count = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        while (left < nums.length && nums[left++] == target)
            count++;
        return count;
    }
}
