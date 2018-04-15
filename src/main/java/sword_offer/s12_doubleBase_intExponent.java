package sword_offer;

//给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
public class s12_doubleBase_intExponent {
    public double Power(double base, int exponent) {
        double result = base;
        if (exponent == 0){
            return 1;
        }else if (exponent > 0){
            for (int i = 1; i < exponent; i++) {    //循环n-1次，因为result = base，已经是exp = 1时的情况
                result *= base;
            }
        }else { // exponent < 0
            if (base == 0){
                throw new RuntimeException("分母不能为0");
            }else {
                for (int i = 1; i < Math.abs(exponent); i++) {
                    result *= base;
                }
            }
        }
        return exponent > 0 ? result : 1/result;
    }

}
