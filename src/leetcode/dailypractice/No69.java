package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/15
 */
public class No69 {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int l = 1;
        int r = x / 2;
        // 在区间 [l,r] 查找目标元素
        while (l < r) {
            // 避免int溢出，改用
            int mid = l + (r - l) / 2;
            // 避免乘法溢出，改用除法
            if (mid > x / l) {
                // 下一轮搜索区间是 [l,mid - 1]
                l = mid - 1;
            } else {
                // 下一轮搜索区间是 [mid,r]
                r = mid;
            }
        }
        return l;
    }
}
