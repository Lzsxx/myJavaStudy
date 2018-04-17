package sword_offer;

// 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。

// 思路：用位运算实现加法操作，先按位异或，等于求各位相加的结果，但不算进位
// 再按位与，然后左移1位，等于求各位的进位。然后再将进位和异或的结果相加。相加的过程是用两个操作数
// 再次进行按位异或、按位与、左移位的操作。直到进位为0

public class s48_add_without_operator {
    public int Add(int num1,int num2) {
        if (num1 == 0 || num2 == 0){
            return num1 == 0 ? num2 : num1;
        }
        while ( num2 != 0 ){
            int onlyPlusBit = num1 ^ num2;
            int carryBit = (num1 & num2) << 1;
            num1 = onlyPlusBit;
            num2 = carryBit;
        }
        return num1;
    }
}
