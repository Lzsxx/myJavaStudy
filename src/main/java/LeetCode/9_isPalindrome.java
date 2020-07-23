package LeetCode;
//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1:
//
// 输入: 121
//输出: true
//
//
// 示例 2:
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3:
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 进阶:
//
// 你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学
// 👍 1158 👎 0


import java.util.LinkedList;
import java.util.Stack;
class IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int originalX = x;
        int reverseValue = 0;
        while (x != 0) {
            int single = x % 10;

            int testOverFlow = reverseValue * 10 + single;
            if ((testOverFlow - single) / 10 != reverseValue) {
                return false;
            } else {
                reverseValue = testOverFlow;
            }
            x = x / 10;
        }

        return originalX == reverseValue;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
