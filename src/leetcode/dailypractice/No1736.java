package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/7/24
 */
public class No1736 {
    /**
     * 1736. 替换隐藏数字得到的最晚时间
     * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     * 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     * <p>
     * 示例 1：
     * 输入：time = "2?:?0"
     * 输出："23:50"
     * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
     * <p>
     * 示例 2：
     * 输入：time = "0?:3?"
     * 输出："09:39"
     * <p>
     * 示例 3：
     * 输入：time = "1?:22"
     * 输出："19:22"
     * <p>
     * <p>
     * 提示：
     * time 的格式为 hh:mm
     * 题目数据保证你可以由输入的字符串生成有效的时间
     */
    public static String maximumTime(String time) {
        char[] chars = time.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                switch (i) {
                    case 0:
                        if (chars[i + 1] < '4' || chars[i + 1] == '?') {
                            sb.append(2);
                        } else {
                            sb.append(1);
                        }
                        break;
                    case 1:
                        if (chars[i - 1] == '1' || chars[i - 1] == '0') {
                            sb.append(9);
                        } else {
                            sb.append(3);
                        }
                        break;
                    case 3:
                        sb.append(5);
                        break;
                    case 4:
                        sb.append(9);
                        break;
                }
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public String maximumTime1(String time) {
        StringBuilder sb = new StringBuilder();
        sb.append(time.charAt(0) == '?' ? (time.charAt(1) == '?' || time.charAt(1) < '4') ? '2' : '1' : time.charAt(0));
        sb.append(time.charAt(1) == '?' ? sb.charAt(0) == '2' ? '3' : '9' : time.charAt(1));
        sb.append(':');
        sb.append(time.charAt(3) == '?' ? '5' : time.charAt(3));
        sb.append(time.charAt(4) == '?' ? '9' : time.charAt(4));
        return sb.toString();
    }

    public static void main(String[] args) {
        String time = "0?:3?";
        System.out.println(No1736.maximumTime(time));
    }
}
