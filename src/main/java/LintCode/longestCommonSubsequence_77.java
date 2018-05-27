package LintCode;

//77. 最长公共子序列
//给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。

public class longestCommonSubsequence_77 {
    public static void main(String[] args) {
        longestCommonSubsequence_77 test = new longestCommonSubsequence_77();
        String A = "13456778";
        String B = "357486782";
        System.out.println(test.longestCommonSubsequence(A, B));;
    }
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        char[] chars_A = A.toCharArray();
        char[] chars_B = B.toCharArray();

        int m = chars_A.length;
        int n = chars_B.length;
        int[][] F = new int[m + 1][n + 1];  // 已默认初始化为0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {  // i\j取对应字符的时候都要先-1
                if (chars_A[i-1] == chars_B[j-1]) {
                    F[i][j] = F[i - 1][j - 1] + 1;
                }else {
                    F[i][j] = Math.max(F[i - 1][j], F[i][j - 1]);
                }
            }
        }

        /***** 输出一个公共子串 *****/
        int LCS_length = F[m][n];
        char[] common = new char[LCS_length];
        int k = 0;  // 表示common数组的下标
        int len = 0;    // 表示当前判断到第几个数量级的最长公共子序列
        // 从上到下
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (F[i][j] > len) {
//                    common[k] = chars_B[j-1];
//                    k++;
//                    len ++;
//                    break;
//                }
//            }
//        }
        // 从左到右
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (F[j][i] > len) {
                    common[k] = chars_B[i-1];
                    k++;
                    len ++;
                    break;
                }
            }
        }

        System.out.println(new String(common));
        return F[m][n];
    }
}
