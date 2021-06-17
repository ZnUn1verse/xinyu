package leetcode.dailypractice;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhangXinYu
 * @date 2021/6/17
 */
public class No65 {
    /**
     * 题目：
     * 65. 有效数字
     * <p>
     * 有效数字（按顺序）可以分成以下几个部分：
     * <p>
     * 一个 小数 或者 整数
     * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * 小数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分有效数字列举如下：
     * <p>
     * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
     * 部分无效数字列举如下：
     * <p>
     * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
     * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "0"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "e"
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：s = "."
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：s = ".1"
     * 输出：true
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 20
     * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
     */

    /**
     * 思路：
     * 校验一个数字是否有效，一个简单的思路就是根据题目所给的信息进行分类讨论
     * <p>
     * 注意到一个有效数字中有两个“关键字”，或者说是标识符，分别是一个 'e' 或 'E', 这是区分 科学计数法数字 和普通数字的标志
     * 小数点 '.'，这是区分小数和整数的标志
     * 所以对于两个标志的识别和查找是分类讨论的前提，然后按照题目信息进行分类查找
     * <p>
     * 1、先遍历一次字符串查找 'e' 或 'E' 的位置，分三种情况
     * 没有 'e' 或 'E' ,说明这是一个 小数 或 整数, 标识符设置到末尾
     * 有且只有一个 'e' 或 'E' ，且位置不在开头结尾，记录其位置，可以将数字分为两半，前面是小数或整数后面是整数
     * 不止一个 'e' 或 'E' 或位于开头结尾，这是不合理的，直接返回 false
     * 2、在前半段查找小数点 '.'（没有指数的情况下指数标识符因为在末尾所以是查找整个字符串），同样有三种情况
     * 没有小数点，那就是整数了，同样把小数标识符设置到末尾
     * 有且只有一个小数点，说明是一个小数，，记录其位置
     * 不止一个小数点，这是不合理的，直接返回 false
     * 3、对整数分情况讨论
     * 有没有符号字符（'+' 或 '-'），是不是有且只有一个
     * 是否有至少一位数字(0开头的数字是合法的)
     * 4、对小数分情况讨论，小数比整数复杂些
     * 有没有符号字符（'+' 或 '-'），是不是有且只有一个
     * 小数点前后至少一个位置有数字
     * 时间复杂度：O(n)O(n)
     * 空间复杂度：O(1)O(1)
     */
    public boolean isNumber(String s) {
        char[] ss = s.toCharArray();
        int n = ss.length;
        // 先检测是否有 e
        int e = n;
        for (int i = 0; i < n; ++i) {
            if (ss[i] == 'e' || ss[i] == 'E') {
                // 两个 e 不合法
                if (e != n) {
                    return false;
                } else {
                    e = i;
                }
            }
        }
        // e 前面没东西，不合法
        if (e == 0) {
            return false;
        }
        // 然后检查 e 前面 是否有小数点
        int dot = e;
        for (int i = 0; i < e; ++i) {
            if (ss[i] == '.') {
                // 两个小数点 不合法
                if (dot != e) {
                    return false;
                } else {
                    dot = i;
                }
            }
        }
        int c = 0;
        // 没有小数点，那就是整数
        if (dot == e) {
            // 整数第一位可能是符号位
            if (ss[c] == '+' || ss[c] == '-') {
                ++c;
                // 只有一个符号位也是不行的
                if (c == dot) {
                    return false;
                }
            }
            // 剩下部分应该全是数字
            while (c < dot) {
                int tmp = ss[c++] - '0';
                if (tmp < 0 || tmp > 9) {
                    return false;
                }
            }
        }
        // 有小数点那就是小数了，范围是 [c,e)
        else {
            // 小数第一位可能是符号位
            if (ss[c] == '+' || ss[c] == '-') {
                ++c;
            }
            // 先考虑第三种情况：一个点 '.' ，后面跟着至少一位数字
            if (c == dot) {
                // 小数点后面没有数字，不合法！
                if ((c++) + 1 == e) {
                    return false;
                }
                while (c < e) {
                    int ttt = ss[c++] - '0';
                    if (ttt < 0 || ttt > 9) {
                        return false;
                    }
                }
            }
            // 情况2：至少一位数字，后面跟着一个点 '.'
            // 情况3：至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
            else {
                // [c, dot) 应该都是数字
                while (c < dot) {
                    int ttt = ss[c++] - '0';
                    if (ttt < 0 || ttt > 9) {
                        return false;
                    }
                }
                // (dot,e) 也应该是数字
                c = dot + 1;
                while (c < e) {
                    int ttt = ss[c++] - '0';
                    if (ttt < 0 || ttt > 9) {
                        return false;
                    }
                }
            }
        }
        // e 后面肯定是整数
        // e 是字符串最后一位，不行!
        if (++e == n) {
            return false;
        }
        // 整数可能有一位符号位
        if (e < n && (ss[e] == '+' || ss[e] == '-')) {
            // 只有一个符号位也是不行的
            if (++e == n) {
                return false;
            }
        }
        // 剩下的应该全部是数字
        while (e < n) {
            int sss = ss[e++] - '0';
            if (sss < 0 || sss > 9) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumber2(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * DFA(有限状态自动机)
     * @param s
     * @return
     */
    public boolean isNumber3(String s) {
        Dfa dfa = new Dfa();
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if (!dfa.getChar(c)) {
                return false;
            }
        }
        return dfa.end.contains(dfa.state);
    }
}


/**
 * DFA(有限状态自动机)
 * 有效数字组成： (整数 | 小数) (e 指数整数)
 * 整数 = 符号 + 数字
 * 小数 = 符号 + 数字 + 小数点 + 数字 (小数点细分为前面有数字的小数点和前面没数字的小数点)
 * <p>
 * 0起始状态：符号、整数数字、前面无数字的小数点
 * 1符号：整数数字、前面无数字的小数点
 * 2整数数字：整数数字、前面有数字的小数点、e/E、终止态
 * 3前面无数字的小数点：小数数字
 * 4前面有数字的小数点：小数数字、e/E、终止态
 * 5小数数字：小数数字、e/E、终止态
 * 6 e/E：指数符号、指数数字
 * 7指数符号：指数数字
 * 8指数数字：指数数字、终止态
 * 9终止态：-1错误态
 */
class Dfa {
    /**
     * 初始状态
     */
    public int state;
    public Set<Integer> end = new HashSet<>(10);

    public Dfa() {
        state = 0;
        end.add(2);
        end.add(4);
        end.add(5);
        end.add(8);
    }

    public boolean getChar(char s) {
        int num = s - '0';
        if (s == '+' || s == '-') {
            if (state == 0) {
                // 符号
                state = 1;
            } else if (state == 6) {
                // 指数符号
                state = 7;
            } else {
                // 错误态
                return false;
            }
        } else if (num >= 0 && num <= 9) {
            if (state < 3) {
                // 整数数字
                state = 2;
            } else if (state < 6) {
                // 小数数字
                state = 5;
            } else if (state < 9) {
                // 指数数字
                state = 8;
            } else {
                // 错误态
                return false;
            }
        } else if (s == 'e' || s == 'E') {
            if (state == 2 || state == 4 || state == 5) {
                state = 6;
            } else {
                return false;
            }
        } else if (s == '.') {
            if (state < 2) {
                // 前面无数字的小数点
                state = 3;
            } else if (state == 2) {
                // 前面有数字的小数点
                state = 4;
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
