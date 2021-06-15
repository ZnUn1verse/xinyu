package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/15
 */
public class No852MountainsArray {
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
