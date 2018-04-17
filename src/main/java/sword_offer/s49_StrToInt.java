package sword_offer;

// 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
// 数值为0或者字符串不是一个合法的数值则返回0

// 思路：就是判断每个字符是不是在char的范围内

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class s49_StrToInt {
    public int StrToInt(String str) {
        if (str.length() < 1){
            return 0;
        }
        char[] chs = str.toCharArray();
        int result = 0;
        boolean flag = true;   // 为true表示正
        if (chs[0] == '-'){
            flag = false;
        }
        for (int i = chs.length - 1; i >= 0 ; i --) {
            // 遇到非数字字符
            if (i == 0 && chs[i] == '+'){
                break;
            }
            if (i == 0 && chs[i] == '-'){
                break;
            }
            if (chs[i] < '0' || chs[i] > '9'){
                return 0;
            }
            int currBit = chs[i] - '0';
            if (flag){
                result += currBit * Math.pow(10, chs.length - 1 - i);
            }else {
                result -= currBit * Math.pow(10, chs.length - 1 - i);
            }
        }
        return result;
    }
}
