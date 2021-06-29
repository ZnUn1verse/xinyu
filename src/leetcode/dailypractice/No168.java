package leetcode.dailypractice;

/**
 * @author ZhangXinYu
 * @date 2021/6/29
 */
public class No168 {
    /**
     * 168. Excel表列名称
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     * <p>
     * 例如，
     * <p>
     * 1 -> A
     * 2 -> B
     * 3 -> C
     * ...
     * 26 -> Z
     * 27 -> AA
     * 28 -> AB
     * ...
     * <p>
     * 示例 1:
     * 输入: 1
     * 输出: "A"
     * <p>
     * 示例 2:
     * 输入: 28
     * 输出: "AB"
     * <p>
     * 示例 3:
     * 输入: 701
     * 输出: "ZY"
     * 通过次数56,340提交次数140,092
     */
    public String convertToTitle(int columnNumber) {
        String res = "";
        while (columnNumber > 0) {
            int end = columnNumber % 26;
            if (end == 0) {
                end = 26;
            }
            res = String.valueOf((char) (end + '@')) + res;
            columnNumber = (columnNumber - end) / 26;
        }
        return res;
    }

    public static void main(String[] args) {
        No168 no168 = new No168();
        System.out.println(no168.convertToTitle(28));
    }
}
