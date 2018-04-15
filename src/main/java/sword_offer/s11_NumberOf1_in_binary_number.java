package sword_offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 题目:给出一个数，求其二进制数中1的个数
// 思路：十进制数如何转换为二进制数
public class s11_NumberOf1_in_binary_number {
    public static void main(String[] args) {
        int a = -10;
        System.out.println(Integer.toBinaryString(a));
    }
    public int NumberOf1(int n) {
        // 采用位运算，每次n & （n-1）,会将最右边的一个1去掉，但最后一个1去掉时，n&(n-1)的值为0
        // 注意：这里所指的最右边的0，不一定是最低位那个，如 1100 & 1011 =1000 ， 去掉的是倒数第3位的1
        int count = 0;
        while (n != 0){
            count ++;
            n = n & (n - 1);
        }
        return count;
    }
}
