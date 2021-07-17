package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/7/17
 */
public class Offer42 {
    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     *
     * 示例1:
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 提示：
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
     */
    /**
     * DP
     * 动态规划解析：
     * 状态定义： 设动态规划列表 dpdp ，dp[i]dp[i] 代表以元素 nums[i]nums[i] 为结尾的连续子数组最大和。
     * <p>
     * 为何定义最大和 dp[i]dp[i] 中必须包含元素 nums[i]nums[i] ：保证 dp[i]dp[i] 递推到 dp[i+1]dp[i+1] 的正确性；如果不包含 nums[i]nums[i] ，递推时则不满足题目的 连续子数组 要求。
     * 转移方程： 若 dp[i-1] \leq 0dp[i−1]≤0 ，说明 dp[i - 1]dp[i−1] 对 dp[i]dp[i] 产生负贡献，即 dp[i-1] + nums[i]dp[i−1]+nums[i] 还不如 nums[i]nums[i] 本身大。
     * <p>
     * 当 dp[i - 1] > 0dp[i−1]>0 时：执行 dp[i] = dp[i-1] + nums[i]dp[i]=dp[i−1]+nums[i] ；
     * 当 dp[i - 1] \leq 0dp[i−1]≤0 时：执行 dp[i] = nums[i]dp[i]=nums[i] ；
     * 初始状态： dp[0] = nums[0]dp[0]=nums[0]，即以 nums[0]nums[0] 结尾的连续子数组最大和为 nums[0]nums[0] 。
     * <p>
     * 返回值： 返回 dpdp 列表中的最大值，代表全局最大值。
     * <p>
     * 空间复杂度降低：
     * 由于 dp[i]dp[i] 只与 dp[i-1]dp[i−1] 和 nums[i]nums[i] 有关系，因此可以将原数组 numsnums 用作 dpdp 列表，即直接在 numsnums 上修改即可。
     * 由于省去 dpdp 列表使用的额外空间，因此空间复杂度从 O(N)O(N) 降至 O(1)O(1) 。
     * 复杂度分析：
     * <p>
     * 时间复杂度 O(N)O(N) ： 线性遍历数组 numsnums 即可获得结果，使用 O(N)O(N) 时间。
     * 空间复杂度 O(1)O(1) ： 使用常数大小的额外空间。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 前缀和。
     * 1.都是负数的情况下 每次都是sum为当前值，依次与maxsum比较取其中最大的。
     * 2. 正常情况下（有正有负）累计前缀和，只要sum大于0 （还有存在价值），就加上来，判断与前面的maxsum谁大，取较大值；
     * 当前和变小到0时（说明前面的负数抵消了，后面来的数不管是正是负，前面累计的和0都没价值了），则重新从当前数开始，同时保证子数组的连续性。
     * 注意不是遇到负数就重新赋值。另外需要不停的判断当前和是不是最大的。
     *
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        // 先默认第一个数为最大值
        int maxsum = nums[0], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当前和不大于0时，说明前面抵消了，从新开始累计和
            // 同样的如果都是负数时，则依次比较哪个最大，赋值给maxsum
            sum = sum <= 0 ? nums[i] : sum + nums[i];
            // 不停比较更新maxsum
            maxsum = sum > maxsum ? sum : maxsum;
        }
        return maxsum;
    }
}
