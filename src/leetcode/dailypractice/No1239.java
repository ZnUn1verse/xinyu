package leetcode.dailypractice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xinyu Zhang
 * @version 1.0
 * @desc
 * @date 2021 2021/6/19 19:30
 */
public class No1239 {
    /**
     * 1239. 串联字符串的最大长度
     * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
     * <p>
     * 请返回所有可行解 s 中最长长度。
     * <p>
     * 示例 1：
     * 输入：arr = ["un","iq","ue"]
     * 输出：4
     * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
     * <p>
     * 示例 2：
     * 输入：arr = ["cha","r","act","ers"]
     * 输出：6
     * 解释：可能的解答有 "chaers" 和 "acters"。
     * <p>
     * 示例 3：
     * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
     * 输出：26
     * <p>
     * <p>
     * 提示：
     * 1 <= arr.length <= 16
     * 1 <= arr[i].length <= 26
     * arr[i] 中只含有小写英文字母
     */
    /**
     * 思路：
     * 方法一：回溯 + 位运算
     * 我们需要计算可行解的长度，至于可行解具体是什么，以及可行解中各个字符的顺序我们是不用考虑的。因此构成可行解的每个字符串均可以视作一个字符集合，
     * 且集合不含重复元素。由于构成可行解的字符串仅包含小写字母，且无重复元素，我们可以用一个二进制数来表示该字符串的字符集合，
     * 二进制的第 i 位为 1 表示字符集合中含有第 i 个小写字母，为 0 表示字符集合中不含有第 i 个小写字母。
     * 由于包含重复字母的字符串无法参与构成可行解，因此遍历arr，从中筛选出无重复字母的字符串，将其对应二进制数加入一数组，记作masks。
     * <p>
     * 接下来，使用回溯法来解决本问题：
     * 我们用backtrack(pos,mask) 表示递归的函数，其中pos 表示我们当前递归到了数组masks 中的第pos 个数mask 表示当前连接得到的字符串对应二进制数为mask；
     * 对于第pos 个数，我们有两种方法：选或者不选。如果mask 和masks[pos] 无公共元素，
     * 则可以选这个数，此时我们调用backtrack(pos+1,mask∣masks[pos]) 进行递归。如果我们不选这个数，那么我们调用backtrack(pos+1,mask) 进行递归。
     * 记masks 的长度为 n，当pos=n 时，计算mask 中1的个数，即为可行解的长度，用其更新可行解的最长长度。
     */
    int ans = 0;

    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<Integer>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int ch = s.charAt(i) - 'a';
                // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                if (((mask >> ch) & 1) != 0) {
                    mask = 0;
                    break;
                }
                // 将 ch 加入 mask 中
                mask |= 1 << ch;
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }

        backtrack(masks, 0, 0);
        return ans;
    }

    public void backtrack(List<Integer> masks, int pos, int mask) {
        if (pos == masks.size()) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }
        // mask 和 masks[pos] 无公共元素
        if ((mask & masks.get(pos)) == 0) {
            backtrack(masks, pos + 1, mask | masks.get(pos));
        }
        backtrack(masks, pos + 1, mask);
    }
}
