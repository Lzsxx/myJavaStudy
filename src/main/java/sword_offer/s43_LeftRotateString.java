package sword_offer;

// 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
// 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
// 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。

// 思路：字符串的旋转，先全部旋转，再分开旋转，或者先分开旋转，再全部旋转都可以
public class s43_LeftRotateString {
    public String LeftRotateString(String str,int n) {
        if (n >= str.length()){
            return str;
        }
        // 先旋转前 n 个
        str = reverse(str, 0, n - 1);
        str = reverse(str, n, str.length() - 1);
        // 再旋转后n ~length -1个
        str = reverse(str,0, str.length() - 1);
        return str;
    }
    public String reverse(String string, int low, int high){
        char[] chs = string.toCharArray();
        while (low < high){
            char temp = chs[low];
            chs[low] = chs[high];
            chs[high] = temp;
            low ++;
            high --;
        }
        return String.valueOf(chs);
    }
}
