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
            if (k == -1 || p[j] == p[k]) {
                j++;
                k++;
                if (p[j] != p[k]) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
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
                j = next[j];
            }
        }
        if (j < t.length) { //如果运行完，最后j会走到t.length处
            return -1;
        } else
            return i - t.length; // 返回模式串在主串中的头下标
    }
}