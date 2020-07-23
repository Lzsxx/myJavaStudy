//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1:
//
// 输入: 123
//输出: 321
//
//
// 示例 2:
//
// 输入: -123
//输出: -321
//
//
// 示例 3:
//
// 输入: 120
//输出: 21
//
//
// 注意:
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
// Related Topics 数学
// 👍 2044 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package LeetCode;
class Reverse {
    public int reverse(int x) {
        /* 思路：从右到左用取模方法取个位数，每次取完除以10，这样取模能取到下一位，x为0时表示已经取完 */
        int reverseValue = 0;
        while (x != 0) {
            int single = x % 10;
            // 已有的值左移1位，加上当前取得的个位数
            int testOverFlow = reverseValue * 10 + single;
            // 判断是否溢出
            if ((testOverFlow - single) / 10 != reverseValue) {
                return 0;
            } else {
                reverseValue = testOverFlow;
            }
            x = x / 10;
        }
        return reverseValue;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        System.out.println(reverse.reverse(-2147483648));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
