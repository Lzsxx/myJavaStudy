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
        if (x == 0) {
            return 0;
        }

        // 2147483648 ~ 2*10^9
        boolean negative = false;
        if (x < 0) {
            negative = true;
        }
        long absx = x;
        absx = Math.abs(absx);
        int[] values = new int[10];
        int index = 0;
        boolean beginFlag = false;
        for (int i = 1; i <= 10; i++) {
            // 获取每一位数字
            int pow = (int) Math.pow(10, (10 - i));
            int temp =(int) (absx / pow);
            // 从第一个不等于0的数开始存储每一位
            if (temp != 0) {
                beginFlag = true;
            }
            if (beginFlag) {
                values[index++]=temp;
                absx = absx - temp * (int) Math.pow(10, (10 - i));
            }
            if (absx <= 0) {
                break;
            }
        }

        long reverseValue = 0L;
        int radix = 0;
        for (int i = 0; i < values.length; i++) {
            reverseValue += (long) values[i] * (long) Math.pow(10, radix);
            radix++;
        }
        if (reverseValue > 2147483647) {
            return 0;
        } else {
            if (negative) {
                reverseValue = 0 - reverseValue;
            }
        }
        return (int)reverseValue;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        System.out.println(reverse.reverse(-2147483648));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
