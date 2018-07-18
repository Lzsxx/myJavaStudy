package LintCode;
//
//29. 交叉字符串
//给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
//样例
//比如 s1 = "aabcc" s2 = "dbbca"
//    - 当 s3 = "aadbbcbcac"，返回  true.
//    - 当 s3 = "aadbbbaccc"， 返回 false.
public class isInterleave_29 {
    public static void main(String[] args) {
        isInterleave_29 test = new isInterleave_29();
        int[] arr = {3,5,8,9,10,17,17,20};
//        System.out.println(ztest.cutting(arr, 8));;
        String a1 = "sdfjas;dfjoisdufzjkndfasdkfja;sdfa;dfa;dfaskdjhfasdhjdfakhdgfkajdfasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfhakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh";
        String a2 = "dfnakdjnfjkzghdufguweygfasjkdfgb2gf8asf7tgbgasjkdfgasodf7asdgfajksdfguayfgaogfsdkagfsdhfajksdvfbgkadsghfakdsfgasduyfgajsdkfgajkdghfaksdgfuyadgfasjkdvfjsdkvfakfgauyksgfajkefgjkdasgfdjksfgadjkghfajksdfgaskdjfgasjkdgfuyaegfasdjkfgajkdfygadjskfgjkadfg";
        String a3 = "sdfjas;dfjoisdfnakdjnfjkzghdufguwdufzjkeygfasjkdfgb2gf8asf7ndtgbgasjkdfgasodf7asdfgfajkasdksdfguayfgaogfsdkagfsfjadhfajksdvfbgkadsghfa;sdkdsfgasduyfgajsdkfgafajkdghfaksdgfuyadgfas;dfjkdvfjsdkvfakfgauyksa;dgfajkefgjkdasgfdjksffaskdjhfasdhjdfakhdgadjkghfajgfkajdfksdfgaskdjfgasjkdgfuasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfyaegfasdjkfgajkdfygadjskfgjkadfghakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh";
        System.out.println(test.isInterleave(a1, a2, a3));
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        char[] s1_char = s1.toCharArray();
        char[] s2_char = s2.toCharArray();
        char[] s3_char = s3.toCharArray();

        if (s1_char.length + s2_char.length != s3_char.length) {
            return false;
        }

        boolean[][] F = new boolean[s1_char.length + 1][s2_char.length + 1];
        F[0][0] = true;
        // 初始化第一行,s1为空的情况
        for (int j = 1; j <= s2_char.length; j++) {
            F[0][j] = F[0][j - 1] && (s2_char[j - 1] == s3_char[j - 1]);
        }
        // 初始化第一列，s2为空的情况
        for (int i = 1; i <= s1_char.length; i++) {
            F[i][0] = F[i - 1][0] && (s1_char[i - 1] == s3_char[i - 1]);
        }
        // 开始处理数组其他值
        for (int i = 1; i <= s1_char.length; i++) {
            for (int j = 1; j <= s2_char.length; j++) {
                if (s1_char[i - 1] == s3_char[i + j - 1] && s2_char[j - 1] == s3_char[i + j - 1]) {
                    F[i][j] = F[i - 1][j] || F[i][j - 1];
                } else if (s1_char[i - 1] == s3_char[i + j - 1]) {
                    F[i][j] = F[i - 1][j];
                } else if (s2_char[j - 1] == s3_char[i + j - 1]) {
                    F[i][j] = F[i][j - 1];
                } else {
                    F[i][j] = false;
                }
            }
        }
        return F[s1_char.length][s2_char.length];
    }
}
