package LintCode;

//给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度。
//样例
//        给出 [5,4,1,2,3]，LIS 是 [1,2,3]，返回 3
//        给出 [4,2,4,5,3,7]，LIS 是 [2,4,5,7]，返回 4


public class longestIncreasingSubsequence_76 {
    public static void main(String[] args) {
        longestIncreasingSubsequence_76 test = new longestIncreasingSubsequence_76();
        int[] arr = {9,3,6,2,7};
//        System.out.println(test.cutting(arr, 8));;
        String a1 = "sdfjas;dfjoisdufzjkndfasdkfja;sdfa;dfa;dfaskdjhfasdhjdfakhdgfkajdfasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfhakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh";
        String a2 = "dfnakdjnfjkzghdufguweygfasjkdfgb2gf8asf7tgbgasjkdfgasodf7asdgfajksdfguayfgaogfsdkagfsdhfajksdvfbgkadsghfakdsfgasduyfgajsdkfgajkdghfaksdgfuyadgfasjkdvfjsdkvfakfgauyksgfajkefgjkdasgfdjksfgadjkghfajksdfgaskdjfgasjkdgfuyaegfasdjkfgajkdfygadjskfgjkadfg";
        String a3 = "sdfjas;dfjoisdfnakdjnfjkzghdufguwdufzjkeygfasjkdfgb2gf8asf7ndtgbgasjkdfgasodf7asdfgfajkasdksdfguayfgaogfsdkagfsfjadhfajksdvfbgkadsghfa;sdkdsfgasduyfgajsdkfgafajkdghfaksdgfuyadgfas;dfjkdvfjsdkvfakfgauyksa;dgfajkefgjkdasgfdjksffaskdjhfasdhjdfakhdgadjkghfajgfkajdfksdfgaskdjfgasjkdgfuasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfyaegfasdjkfgajkdfygadjskfgjkadfghakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh";
        System.out.println(test.longestIncreasingSubsequence(arr));
    }


    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums.length < 1) {
            return 0;
        }
        int[] F = new int[nums.length];
        F[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int temp = 1;
            int max = temp;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    temp = F[j] + 1;
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
            F[i] = max;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (F[i] > max) {
                max = F[i];
            }
        }
        return max;
    }
}
