package leetcode.dailypractice;

import java.util.*;

public class Mian10_02 {
    /**
     * 面试题 10.02. 变位词组
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     * 注意：本题相对原题稍作修改
     * <p>
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String key = String.valueOf(ch);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

    public static void main(String[] args) {
        Mian10_02 m = new Mian10_02();
        System.out.println(m.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).toString());;
    }
}
