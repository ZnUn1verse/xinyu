package leetcode.dailypractice;

import java.util.*;

/**
 * @author ZhangXinYu
 * @date 2021/6/25
 */
public class No752 {
    /**
     * 752. 打开转盘锁
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     * <p>
     * 示例 1:
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     * <p>
     * 示例 2:
     * 输入: deadends = ["8888"], target = "0009"
     * 输出：1
     * 解释：
     * 把最后一位反向旋转一次即可 "0000" -> "0009"。
     * <p>
     * 示例 3:
     * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1
     * 解释：
     * 无法旋转到目标数字且不被锁定。
     * <p>
     * 示例 4:
     * 输入: deadends = ["0000"], target = "8888"
     * 输出：-1
     * <p>
     * <p>
     * 提示：
     * 1 <= deadends.length <= 500
     * deadends[i].length == 4
     * target.length == 4
     * target 不在 deadends 之中
     * target 和 deadends[i] 仅由若干位数字组成
     */
    /**
     * 思路：双向bfs
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        //不需要变换直接成功
        if ("0000".equals(target)) {
            return 0;
        }
        HashSet<String> ds = new HashSet<>();
        for (String d : deadends) {
            ds.add(d);
        }
        //上来就死了
        if (ds.contains("0000")) {
            return -1;
        }
        //存搜过的节点信息
        HashSet<String> seen = new HashSet<>();
        seen.add("0000");
        HashSet<String> beginSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();
        beginSet.add("0000");
        endSet.add(target);
        int step = 0;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            //bfs向外延伸一圈step+1
            step++;
            //优先搜索长度短的放在beginSet中
            if (beginSet.size() > endSet.size()) {
                HashSet<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }
            HashSet<String> newBeginSet = new HashSet<>();
            //搜beginSet
            for (String curr : beginSet) {
                //当前串能变换的那几个串
                List<String> nextStr = nextStr(curr);
                for (String next : nextStr) {
                    //由于end已经算过了
                    if (endSet.contains(next)) {
                        return step;
                    }
                    if (!seen.contains(next) && !ds.contains(next)) {
                        newBeginSet.add(next);
                        seen.add(next);
                    }
                }
            }
            beginSet = newBeginSet;
        }
        //没搜到
        return -1;
    }

    //从str变换的所有字符(8个)
    public List<String> nextStr(String str) {
        List<String> list = new ArrayList<>();
        //e.p. '0000'
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //'0'
            char num = chars[i];
            //+1
            chars[i] = num == '9' ? '0' : (char) (num + 1);
            list.add(new String(chars));
            //-1
            chars[i] = num == '0' ? '9' : (char) (num - 1);
            list.add(new String(chars));
            //重置当前位，给下次循环其他位置用
            chars[i] = num;
        }
        return list;
    }
}
