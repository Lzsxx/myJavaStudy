package algorithm;

public class KmpSort {
    public static void main(String[] args) {
        String target = "bbc abcdab abcdabd abde";// 主串
        String mode = "abcdabd";// 模式串
        char[] t = target.toCharArray();
        char[] m = mode.toCharArray();
        System.out.println(kmpSort(t, m)); // KMP匹配字符串
    }

    //计算部分匹配表
    public static int[] next(char[] p) {
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {  // p[k]表示前缀结束位置，p[j]表示后缀结束位置
                j++;
                k++;
                next[j] = k;    //相同的情况下，两者最大前缀长度又可以+1

//                if (p[j] != p[k]) { // 那么意味着k==-1,前面肯定没有相同的字符可以继承，当前字符又不相等，可以直接等于k的值-1
//                    next[j] = k;
//                } else {
//                    next[j] = next[k];
//                }
            } else {
                k = next[k];    //相当于后缀j不动，前缀k向前移动，到不确定是否相等的那一位，开始下一轮比较，此时前缀要求匹配的长度缩短了，
            }
        }
        return next;
    }

    //字符串匹配
    public static int kmpSort(char[] s, char[] t) {
        int[] next = next(t);
        int i = 0;  //指向源字符串
        int j = 0;  //指向模式字符串
        while (i <= s.length - 1 && j <= t.length - 1) {
            if (j == -1 || s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];    // next[]数组里存储的是下标j之前的多少个值，与字符开头的多少个值是相同的，下一刻只需要从相同的下一个开始比较即可
            }
        }
        if (j < t.length) { //如果运行完，最后j会走到t.length处
            return -1;
        } else
            return i - t.length; // 返回模式串在主串中的头下标
    }
}