package LeetCode;
//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串
// 👍 1180 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) {
            return "";
        }

        String common = strs[0];
        for (int j = 0; j < common.length(); j++) {
            for (int i = 1; i < strs.length; i++) {
                int currLen = common.length();
                // 如果待比较的字符串长度不够，或者前几位对比不满足，就去掉一位重新来
                if (currLen > strs[i].length() || !common.equals(strs[i].substring(0, currLen))) {
                    // 如果长度大于1，就去掉一位
                    if (currLen > 1) {
                        common = common.substring(0, currLen - 1);
                        j = -1;
                        break;
                    } else {
                        return "";  //
                    }
                }
            }
        }
        return common;
    }

    public static void main(String[] args) {
        System.out.println("length:"+"".length());

        String[] strs = {"dog","racecar","car"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String common = longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(common);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
