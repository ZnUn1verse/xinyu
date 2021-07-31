package leetcode.dailypractice;

import java.util.Arrays;

public class No1337 {

    /**
     * 1337. 矩阵中战斗力最弱的 K 行
     * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
     * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     * <p>
     * 示例 1：
     * 输入：mat =
     * [[1,1,0,0,0],
     * [1,1,1,1,0],
     * [1,0,0,0,0],
     * [1,1,0,0,0],
     * [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     * <p>
     * 示例 2：
     * 输入：mat =
     * [[1,0,0,0],
     * [1,1,1,1],
     * [1,0,0,0],
     * [1,0,0,0]],
     * k = 2
     * 输出：[0,2]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 1
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 1
     * 从最弱到最强对这些行排序后得到 [0,2,3,1]
     * <p>
     * <p>
     * 提示：
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] 不是 0 就是 1
     */
    public static int[] kWeakestRows(int[][] mat, int k) {
        int[] ret = new int[k];
        int[][] arr = new int[mat.length][2];
        for (int i = 0; i < mat.length; i++) {
            //0存放当前矩阵的行数，1存放当前平民第一次出现的位置
            arr[i][0] = i;
            arr[i][1] = binarySearch(mat[i]);
        }
        //排序,如果平民出现位置相同，则按照索引位排序
        Arrays.sort(arr, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < k; i++) {
            ret[i] = arr[i][0];
        }
        return ret;
    }

    /**
     * 二分搜索,找出第一个出现的平民
     *
     * @param arr
     * @return
     */
    public static int binarySearch(int[] arr) {
        //第一个就是平民直接返回
        if (arr[0] == 0) {
            return 0;
        }
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //是平民 且上一个是军人
            if (arr[mid] == 0 && arr[mid - 1] == 1) {
                return mid;
            } else if (arr[mid] > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return arr.length;
    }

}
