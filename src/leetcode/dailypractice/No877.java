package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/16
 */
public class No877 {
    public static boolean stoneGame(int[] piles) {
        if (piles == null || piles.length <= 0) {
            return false;
        }
        int length = piles.length;
        int[] dp = new int[length]; // dp[i] 表示 取i + 1次石子堆，亚历克斯比李用 多拿的石子数
        for (int i = 0; i < length; i++) {  // 将dp数组，初始化为 对应石子堆的石子数
            dp[i] = piles[i];
        }
        /*
            遍历 piles数组，计算并填充dp数组
        */
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                // 当 亚历克斯 取了第i堆石子堆 时，李用可能会取 第i+1堆，或者 当前石子堆的最后一堆
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] > 0;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 5};
        System.out.println(No877.stoneGame(arr));
    }
}
