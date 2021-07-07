package leetcode.dailypractice;

import java.util.*;

/**
 * @author ZhangXinYu
 * @date 2021/7/7
 */
public class No451 {
    /**
     * 451. 根据字符出现频率排序
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     * <p>
     * 示例 1:
     * 输入:
     * "tree"
     * 输出:
     * "eert"
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * <p>
     * 示例 2:
     * 输入:
     * "cccaaa"
     * 输出:
     * "cccaaa"
     * 解释:
     * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * <p>
     * 示例 3:
     * 输入:
     * "Aabb"
     * 输出:
     * "bbAa"
     * 解释:
     * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
     * 注意'A'和'a'被认为是两种不同的字符。
     */
    public static String frequencySort(String s) {
        StringBuffer sb = new StringBuffer();
        Map<String, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(String.valueOf(ch), map.getOrDefault(String.valueOf(ch), 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //降序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String, Integer> mapping : list) {
            for(int i = 0; i < mapping.getValue(); i++){
                sb.append(mapping.getKey());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(No451.frequencySort("ttreee"));;
    }
}
