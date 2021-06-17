package leetcode.dailypractice;

/**
 * @author Xinyu Zhang
 * @version 1.0
 * @desc
 * @date 2021 2021/6/18 1:21
 */
public class No483 {
    /**
     * 483. 最小好进制
     * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
     *
     * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
     *
     * 示例 1：
     * 输入："13"
     * 输出："3"
     * 解释：13 的 3 进制是 111。
     *
     * 示例 2：
     * 输入："4681"
     * 输出："8"
     * 解释：4681 的 8 进制是 11111。
     *
     * 示例 3：
     * 输入："1000000000000000000"
     * 输出："999999999999999999"
     * 解释：1000000000000000000 的 999999999999999999 进制是 11。
     *
     * 提示：
     * n的取值范围是 [3, 10^18]。
     * 输入总是有效且没有前导 0。
     */

    /**
     * N = k^0 + k^1 + k^2 + ... + k^m
     *
     * @param n
     * @return
     */
    public static String smallestGoodBase(String n) {
        long N = Long.parseLong(n);
        // 求m的最大值
        long mMax = (long) (Math.log((double) N) / Math.log((double) 2.0));
        System.out.println(Math.log((double) N));
        System.out.println(Math.log((double) 2.0));
        for (long m = mMax; m > 1; m--) {
            //最重要的一步 , m确定之后 k其实也是确定的，这是因为 k^m < N且有(k+1)^m > N
            //因此只需要枚举 [2,log( N )]，并求解对应的K
            long k = (long) Math.pow((double) N, 1.0 / m);
            long s = 0;
            for (long i = 0; i <= m; i++) {
                s = s * k + 1;
            }
            if (s == N) {
                return String.valueOf(k);
            }
        }
        return String.valueOf(N - 1);
    }

    public static void main(String[] args) {
        System.out.println(No483.smallestGoodBase("13"));
    }
}
