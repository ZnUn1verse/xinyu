package leetcode.dailypractice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangXinYu
 * @date 2021/6/21
 */
public class No401 {
    /**
     * 401. 二进制手表
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
     * 例如，下面的二进制手表读取 "3:25" 。
     *
     * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
     *
     * 小时不会以零开头：
     * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
     *
     * 分钟必须由两位数组成，可能会以零开头：
     * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
     *
     * 示例 1：
     * 输入：turnedOn = 1
     * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
     *
     * 示例 2：
     * 输入：turnedOn = 9
     * 输出：[]
     *
     * 提示：0 <= turnedOn <= 10
     */

    /**
     * 思路：
     * 在10个灯中选num个灯点亮，如果选择的灯所组成的时间已不合理（小时超过11，分钟超过59）就进行剪枝
     * 也就是从0到10先选一个灯亮，再选当前灯的后面的灯亮，再选后面的灯的后面的灯亮，一直到num个灯点满
     * 具体思路
     * 为了方便计算，分别设置了小时数组和分钟数组
     * 递归的四个参数分别代表：剩余需要点亮的灯数量，从索引index开始往后点亮灯，当前小时数，当前分钟数
     * 每次进入递归后，先判断当前小时数和分钟数是否符合要求，不符合直接return
     * for循环枚举点亮灯的情况，从index枚举到10，每次枚举，
     * 减少一个需要点亮的灯数量num - 1
     * 从当前已点亮的灯后面选取下一个要点亮的灯 i + 1
     * 在hour中增加当前点亮灯的小时数，如果i大于3，当前灯是分钟灯而不是小时灯，则加上0个小时
     * 在minute中增加当前点亮灯的分钟数，如果i没有大于3，当前灯是小时灯而不是分钟灯，则加上0分钟
     * 当剩余需要点亮的灯数量为0的时候，已枚举完一种情况，根据题目要求的格式加到res列表中
     * 返回res
     */
    int[] hours = new int[]{1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
    int[] minutes = new int[]{0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
    List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int num) {
        backtrack(num, 0, 0, 0);
        return res;
    }

    public void backtrack(int num, int index, int hour, int minute){
        if(hour > 11 || minute > 59)
            return;
        if(num == 0){
            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(':');
            if (minute < 10) {
                sb.append('0');
            }
            sb.append(minute);
            res.add(sb.toString());
            return;
        }
        for(int i = index; i < 10; i++){
            backtrack(num - 1, i + 1, hour + hours[i], minute + minutes[i]);
        }
    }
}
