package leetcode.dailypractice;

import java.util.*;

/**
 * @author ZhangXinYu
 * @date 2021/6/22
 */
public class Offer38 {
    /**
     * 剑指 Offer 38. 字符串的排列
     * 输入一个字符串，打印出该字符串中字符的所有排列。你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     *
     * 限制：1 <= s 的长度 <= 8
     */
    List<String> res = new LinkedList<>();
    char[] c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    public void dfs(int x) {
        if (x == c.length - 1) {
            // 添加排列方案
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<Character>();
        for (int i = x; i < c.length; i++) {
            // 重复，因此剪枝
            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            // 交换，将 c[i] 固定在第 x 位
            swap(i, x);
            // 开启固定第 x + 1 位字符
            dfs(x + 1);
            // 恢复交换
            swap(i, x);
        }
    }

    public void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    public static void main(String[] args) {
        Offer38 offer = new Offer38();
        String[] str = offer.permutation("abc");
        System.out.print("[ ");
        for(String s : str){
            System.out.print(s + " ");
        }
        System.out.print("]");
    }
}
