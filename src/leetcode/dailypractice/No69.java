package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/15
 */
public class No69 {
    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return
     *
     * 思路：
     * 注意到题目中给出的例 2，小数部分将被舍去。我们就知道了，如果一个数 aa 的平方大于 xx ，那么 aa 一定不是 xx 的平方根。我们下一轮需要在 [0..a - 1][0..a−1] 区间里继续查找 xx 的平方根。
     */
    public static int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
//            int mid = (l + r + 1) >> 1;
            int mid = l + (r - l) / 2;
            if (mid < x / mid)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    /**
     * java中的Math.sqrt()方法，对比测试
     *
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        return (int) Math.sqrt(x);
    }

    public static void main(String[] args) {
        System.out.println(No69.mySqrt2(8));
    }
}
