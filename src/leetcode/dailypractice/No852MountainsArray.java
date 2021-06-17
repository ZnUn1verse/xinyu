package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/15
 */
public class No852MountainsArray {
    /**
     * 852. 山脉数组的峰顶索引
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [0,1,0]
     * 输出：1
     * 示例 2：
     * <p>
     * 输入：arr = [0,2,1,0]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：arr = [0,10,5,2]
     * 输出：1
     * 示例 4：
     * <p>
     * 输入：arr = [3,4,5,1]
     * 输出：2
     * 示例 5：
     * <p>
     * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
     * 输出：2
     *
     * @param arr
     * @return
     *
     * 思路：
     * 由于 arr 数值各不相同，因此峰顶元素左侧必然满足严格单调递增，峰顶元素右侧必然不满足。
     * 因此 以峰顶元素为分割点的 arr 数组，根据与 前一元素/后一元素 的大小关系，具有二段性：
     * 峰顶元素左侧满足 arr[i-1] < arr[i]arr[i−1]<arr[i] 性质，右侧不满足
     * 峰顶元素右侧满足 arr[i] > arr[i+1]arr[i]>arr[i+1] 性质，左侧不满足
     *
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (left == mid) {
                return arr[left] > arr[right] ? left : right;
            } else if (arr[left] > arr[mid]) {
                right = mid - 1;
            } else if (arr[right] > arr[mid]) {
                left = mid + 1;
            } else {
                left++;
                right--;
            }
        }
        return left;
    }

    public static int peakIndexInMountainArray2(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (arr[mid] > arr[mid - 1]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        System.out.println(No852MountainsArray.peakIndexInMountainArray2(arr));
    }
}
