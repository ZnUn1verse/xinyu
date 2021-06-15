package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/15
 */
public class No69 {
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
