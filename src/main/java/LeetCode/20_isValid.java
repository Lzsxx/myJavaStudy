package LeetCode;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串
// 👍 1707 👎 0


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class isValid {
    /*
     * 思路：每取一个符号，比较栈顶圆度，如果是对应的另一半，就出栈，否则当前元素入栈
     */

    private static Map<Character, Character> map = new HashMap<>();

    static{
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
    }

    public boolean isValid(String s) {

        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (isNeedPush(ch, stack)) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNeedPush(char ch, LinkedList<Character> stack) {
        if (stack.isEmpty()) {
            return true; // 栈为空，直接入栈
        }
        // 找到对应的另一半，如果栈顶元素就是另一半，则不入栈
        Character another = map.get(ch);
        if (another != null && another.equals(stack.peek())) {
            return false;
        }
        // 默认都入栈
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
