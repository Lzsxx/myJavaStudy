package LeetCode;//实现 strStr() 函数。
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。
//
// 示例 1:
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
//
//
// 示例 2:
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
//
//
// 说明:
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
// Related Topics 双指针 字符串
// 👍 507 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class strStr {
    public int strStr(String haystack, String needle) {
        // 模式字符串为空，返回0
        if (needle.equals("")) {
            return 0;
        }
        // 模式串不为0，被匹配的字符串为0，返回-1
        if (haystack.equals("") || haystack.length() < needle.length()) {
            return -1;
        }
        char[] targets = haystack.toCharArray();
        char[] patterns = needle.toCharArray();

        // 拿haystack的每一个字符依次和needle的每一个字符比较，如果haystack的某个字符（A）能与needle的第一个字符匹配，就继续匹配他们的下一个，直到needle匹配完，
        // 如果中途哪里断了，haystack从A的下一个开始，needle从0开始
        for (int i = 0; i < targets.length; i++) {
            // 如果首字母开始匹配
            if (targets[i] == patterns[0]) {
                // 如果后面长度的已经不够匹配，直接返回-1
                if (i + patterns.length > targets.length) {
                    return -1;
                }

                int j;
                for (j = 1; j < patterns.length; j++) {
                    if (targets[i + j] != patterns[j]) {
                        break;
                    }
                }
                // 如果顺利比较完
                if (j == patterns.length) {
                    return i;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
